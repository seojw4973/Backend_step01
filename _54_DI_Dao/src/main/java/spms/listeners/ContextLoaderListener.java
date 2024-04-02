package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.dao.MemberDao;

/* 어플리케이션이 실행되었을 때 자동으로 호출되는 클래스
 * ServletContext영역이 준비되었습니다...
 * */
// @WebListener 이런식으로 애노테이션을 주거나, web.xml에 추가해서 사용
public class ContextLoaderListener implements ServletContextListener{
	
//	Connection conn;
//	DBConnectionPool connPool;
//	BasicDataSource ds;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextInitialized 호출");
		try {
			ServletContext sc = sce.getServletContext();
			
			// Tomcat이 실행될 때 생성되는 DataSource객체를 찾아서 MemberDao에 주입한다.
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(
						"java:comp/env/jdbc/studydb");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
					
			sc.setAttribute("/auth/login.do", new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogOutController());
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
			
			// sc.setAttribute("memberDao", memberDao);
					
		}catch(Exception e) {
			
		}
	
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextDestroyed 호출");
		try {
			/* Tomcat의 context.xml의 설정 중에
			 * closeMethod="close"를 하면
			 * Tomcat이 종료되면 자동으로 DataSource의 close()를 호출하도록 설정했으므로
			 * 여기서는 close()하면 안된다.
			 * 
			 * 왜냐면 다른 Application이 DataSource를 사용할 수 있으므로
			 * 
			 * */
			
//			if(ds != null) 
//				ds.close();
//			connPool.closeAll();
//			if(conn!= null && conn.isClosed()==false)
//				conn.close();
		}catch(Exception e) {
			
		}
	
	}
}
