package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import spms.vo.Member;

@WebServlet("/member/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet{
	
	// get : 화면에 정보나 페이지를 보여주는 역할
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/member/MemberForm.jsp");
		rd.forward(req, resp);
	}
	
	// post : 주로 정보를 전달받아서 db에 저장하는 역할
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddServlet::doPost() 호출");

//		Connection conn = null;
//
//		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			Class.forName(sc.getInitParameter("driver"));
//			conn = (Connection)sc.getAttribute("conn"); 
			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
			
			memberDao.insert(new Member()
					.setEmail(req.getParameter("email"))
					.setName(req.getParameter("name"))
					.setPassword(req.getParameter("password")));
			
			resp.sendRedirect("list");
			

		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
		/*
		finally {
			// Connection 객체를 1개 생성해서 ServletContext 영역에 보관하고
			// 모든 Servlet이 공유해서 사용하므로 이곳에서 닫으면 안된다.
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
		}
		*/
	}
}









