package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

public class LogOutController implements Controller {
  @Override
  public String execute(Map<String, Object> model) throws Exception {
	System.out.println("LogOutController::execute() - get 요청");
	  
	HttpSession session = (HttpSession)model.get("session");
    session.invalidate();
    
    return "redirect:login.do";
  }
}


//package spms.controls;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@SuppressWarnings("serial")
//@WebServlet("/auth/logout")
//public class LogOutController extends HttpServlet {
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// 세션 저장소에 보관한 회원정보(로그인 정보)를 삭제한다.
//		HttpSession session = req.getSession();
//		session.removeAttribute("member");		// 세션에 저장된 정보 삭제
//		session.invalidate();					// 세션 저장소를 초기화
//		
//		req.setAttribute("viewUrl", "redirect:login.do");
//		
//	}
//	
//}
