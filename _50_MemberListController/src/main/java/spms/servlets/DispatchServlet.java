package spms.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.controls.Controller;
import spms.controls.MemberListController;
import spms.vo.Member;

/* DispatchServlet은 Spring에서 사용하는 DesignPattern을 사용한 클래스 명칭이다.
 * 이 역할은 *.do로 들어오는 모든 주소를 일단 받아서 분기시켜주는 역할이다.
 * 이 서블릿을 설계상에서 FrontController라고 부른다.
 * 
 * */

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatchServlet extends HttpServlet {

	// get요청과 post요청을 모두 받기 위해 구현하는 메소드
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		
		// 요청 주소를 얻는다. 이 주소에 따라 pageController를 찾아서 분기처리한다.
		String servletPath = req.getServletPath();
		System.out.println("DispatchServlet::service() - servletPath=" + servletPath);
		
		// Page Controller한테 전달할 Map 객체를 준비한다.
		/* Map 인터페이스
		 * HashMap은 단일스레드에서 사용
		 * Hashtable은 멀티스레드에서 사용
		 * ConcurrentHashMap은 멀티스레드에서 사용하되, Hashtable보다 속도가 보편적으로 빠른 편이다.
		 * */
		Map<String, Object> model = new ConcurrentHashMap<>();
		model.put("memberDao", this.getServletContext().getAttribute("memberDao"));
		model.put("session", req.getSession());
		
		// pageController 객체를 담을 수 있는 부모 변수
		Controller pageController = null;
		
		try {
			String pageControllerPath = null;

			if("/member/list.do".equals(servletPath)) {
				// 기존에 Servlet을 호출하던 구조에서 일반 Java 객체를 호출하는 방식으로 변경
				// pageControllerPath = "/member/list";
				pageController = new MemberListController();
			}else if("/member/add.do".equals(servletPath)) {
				pageControllerPath = "/member/add";
				if(req.getParameter("email") != null) {
					req.setAttribute("member", new Member()
											.setEmail(req.getParameter("email"))
											.setPassword(req.getParameter("password"))
											.setName(req.getParameter("name")));
				}
			}else if("/member/update.do".equals(servletPath)) {
				pageControllerPath = "/member/update";
				if(req.getParameter("email") != null) {
					req.setAttribute("member", new Member()
										.setNo(Integer.parseInt(req.getParameter("no")))
										.setEmail(req.getParameter("email"))
										.setName(req.getParameter("name")));
				}
			}else if("/member/delete.do".equals(servletPath)) {
				pageControllerPath = "/member/delete";
			}else if("/auth/login.do".equals(servletPath)) {
				pageControllerPath = "/auth/login";
			}else if("/auth/logout.do".equals(servletPath)) {
				pageControllerPath = "/auth/logout";
			}
			
					
			String viewUrl = "";	// 다음에 이동할 jsp나 redirect 경로
			
			// pageController 객체가 존재한다면
			if(pageController != null) {
				System.out.println("DispatcherServlet::service() - pageController="
													  + pageController.getClass().getName());
				viewUrl = pageController.execute(model);
				
				for(String key : model.keySet()) {
					req.setAttribute(key, model.get(key));
				}
			}
			// 아직 pageController가 존재하지 않고, Servlet으로 되어 있을 때
			else {
				System.out.println("DispatcherServlet::service() - pageController=" + pageControllerPath);
				RequestDispatcher rd = req.getRequestDispatcher(pageControllerPath);
				rd.include(req, resp);
				
				viewUrl = (String)req.getAttribute("viewUrl");
			}
			
			
			System.out.println("DispatchServlet::service() - viewUrl =" + viewUrl);
			System.out.println("");
			
			if(viewUrl.startsWith("redirect:")){
				resp.sendRedirect(viewUrl.substring("redirect:".length()));
				return;
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher(viewUrl);
				rd.include(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}
