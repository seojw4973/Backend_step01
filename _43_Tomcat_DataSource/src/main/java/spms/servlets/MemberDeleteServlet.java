package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

@WebServlet("/member/delete")
@SuppressWarnings("serial")
public class MemberDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberDeleteServlet::doGet() 호출");
		req.setCharacterEncoding("UTF-8");
		
//		Connection conn = null;
//		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

			memberDao.delete(Integer.parseInt(req.getParameter("no")));
			
			resp.sendRedirect("list");
		
		}catch (Exception e){
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
//			throw new ServletException(e);
		}
		/*
		finally {
			try { if(stmt!=null) stmt.close();} catch(Exception e) {}
//			try { if(conn!=null) conn.close();} catch(Exception e) {}
		}
		*/
	
	}
	
}
