package spms.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class MemberAddController implements Controller{
	
	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}	
	
		
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(model.get("member") == null) {			// get 요청
			System.out.println("MemberAddController::execute() - get 요청");
			return "/member/MemberForm.jsp";
			
		}else {										// post 요청
			System.out.println("MemberAddController::execute() - post 요청");
			
			// MemberDao memberDao = (MemberDao)model.get("memberDao");
			Member member = (Member)model.get("member");
			memberDao.insert(member);
			
			return "redirect:list.do";
		}
	}

}









