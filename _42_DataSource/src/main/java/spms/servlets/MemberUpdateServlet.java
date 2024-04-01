package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/update")
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberUpdateServlet::doGet() 호출");
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

			Member member = memberDao.selectOne(Integer.parseInt(req.getParameter("no")));
			
			req.setAttribute("member", member);
			
			RequestDispatcher rd = req.getRequestDispatcher(
					"/member/MemberUpdateForm.jsp");
			rd.forward(req, resp);		
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
		/*
		finally {
			try { if(rs!=null) rs.close();} catch(Exception e) {}
			try { if(stmt!=null) stmt.close();} catch(Exception e) {}
			try { if(conn!=null) conn.close();} catch(Exception e) {}
		}
		*/
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberUpdateServlet::doPost() 호출");
		// 한글이 깨지지 않기 위해 값을 꺼내기 전에 설정
		// req.setCharacterEncoding("UTF-8");
		
//		Connection conn = null;
//		PreparedStatement stmt = null;
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			memberDao.update(new Member()
					.setNo(Integer.parseInt(req.getParameter("no")))
					.setName(req.getParameter("name"))
					.setEmail(req.getParameter("email")));

			resp.sendRedirect("list");
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
		/*
		finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
//			try {if(conn!=null) conn.close();} catch(Exception e) {}
		}
		*/
	}
}
