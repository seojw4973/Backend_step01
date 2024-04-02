package spms.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.dao.MysqlMemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller {
	
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}	
	
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// MemberDao memberDao = (MemberDao)model.get("memberDao");
		
		if(model.get("member") == null) {		// get 요청
			System.out.println("MemberUpdateController::execute() - get 요청");
			
			Integer no = (Integer)model.get("no");
			Member member = memberDao.selectOne(no);			
			model.put("member", member);						
			
			return "/member/MemberUpdateForm.jsp";
			
		}else {									// post 요청
			System.out.println("MemberUpdateController::execute() - post 요청");
			Member member = (Member)model.get("member");
			memberDao.update(member);
			
			return "redirect:list.do";
		}
		
		
		
	}
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("MemberUpdateServlet::doGet() 호출");
//		try {
//			ServletContext sc = this.getServletContext();
//			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//
//			Member member = memberDao.selectOne(Integer.parseInt(req.getParameter("no")));
//			
//			req.setAttribute("member", member);
//			req.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
//			/*
//			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
//			rd.forward(req, resp);		
//			*/
//		}catch(Exception e) {
//			throw new ServletException(e);
//			/*
//			e.printStackTrace();
//			req.setAttribute("error", e);
//			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
//			rd.forward(req, resp);
//			*/
//		}
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("MemberUpdateServlet::doPost() 호출");
//		
//		try {
//			ServletContext sc = this.getServletContext();
//			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			Member member = (Member)req.getAttribute("member");
//			
//			memberDao.update(member);
//			
//			req.setAttribute("viewUrl", "redirect:list.do");
//			/*
//			memberDao.update(new Member()
//					.setNo(Integer.parseInt(req.getParameter("no")))
//					.setName(req.getParameter("name"))
//					.setEmail(req.getParameter("email")));
//
//			resp.sendRedirect("list");
//			*/
//		}catch(Exception e) {
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

	
}
