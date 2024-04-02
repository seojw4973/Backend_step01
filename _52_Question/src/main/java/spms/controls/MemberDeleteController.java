package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;

public class MemberDeleteController implements Controller {
  @Override
  public String execute(Map<String, Object> model) throws Exception {
	System.out.println("MemberDeleteController::execute() - get 요청");
	  
	MemberDao memberDao = (MemberDao)model.get("memberDao");
    
    Integer no = (Integer)model.get("no");
    memberDao.delete(no);
    
    return "redirect:list.do";
  }
}


//package spms.controls;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import spms.dao.MemberDao;
//
//@WebServlet("/member/delete")
//@SuppressWarnings("serial")
//public class MemberDeleteController extends HttpServlet {
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("MemberDeleteServlet::doGet() 호출");
//		req.setCharacterEncoding("UTF-8");
//				
//		try {
//			ServletContext sc = this.getServletContext();
//			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//
//			memberDao.delete(Integer.parseInt(req.getParameter("no")));
//			req.setAttribute("viewUrl", "redirect:list.do");
////			resp.sendRedirect("list");
//		
//		}catch (Exception e){
//			throw new ServletException(e);
//			/*
//			e.printStackTrace();
//			req.setAttribute("error", e);
//			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
//			rd.forward(req, resp);
//			*/
//		}
//	
//	}
//	
//}
