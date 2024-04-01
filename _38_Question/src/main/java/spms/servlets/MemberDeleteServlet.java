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
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			memberDao.delete(Integer.parseInt(req.getParameter("no")));
//			Class.forName(sc.getInitParameter("driver"));
			
//			conn = DriverManager.getConnection(
////					"jdbc:mysql://localhost:4306/studydb",	// JDBC url
////					"study",								// id
////					"study");								// password
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")
//					);
			
			stmt = conn.prepareStatement(
					"DELETE FROM members WHERE mno=?");
			stmt.setInt(1, Integer.parseInt(req.getParameter("no")));
			stmt.executeUpdate();
			
			resp.sendRedirect("list");
			
			
			
		}catch (Exception e){
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
//			throw new ServletException(e);
		}finally {
			try { if(stmt!=null) stmt.close();} catch(Exception e) {}
//			try { if(conn!=null) conn.close();} catch(Exception e) {}
		}
	
	}
	
}
