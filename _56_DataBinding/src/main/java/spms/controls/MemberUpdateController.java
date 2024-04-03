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

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.MysqlMemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller, DataBinding {
	
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}	

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"member", spms.vo.Member.class
		};
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
	
}
