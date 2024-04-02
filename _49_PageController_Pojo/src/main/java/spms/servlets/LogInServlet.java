package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LogInServlet::doGet() 호출");
		
		req.setAttribute("viewUrl", "/auth/LogInForm.jsp");
		/*
		RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(req, resp);
		*/
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LogInServlet::doPost() 호출");
		
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
		      
		      Member member = memberDao.exist(
		    		  req.getParameter("email"), 
		    		  req.getParameter("password"));
		      if (member != null) {
		        HttpSession session = req.getSession();
		        session.setAttribute("member", member);
		        req.setAttribute("viewUrl", "redirect:../member/list.do");
//		        resp.sendRedirect("../member/list");

		      } else {
		    	req.setAttribute("viewUrl", "/auth/LogInFail.jsp");
		    	/*
		        RequestDispatcher rd = req.getRequestDispatcher(
		            "/auth/LogInFail.jsp");
		        rd.forward(req, resp);
		        */
		      }			
			
		}catch(Exception e) {
			throw new ServletException(e);
			/*
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			*/
		}
	}
}
