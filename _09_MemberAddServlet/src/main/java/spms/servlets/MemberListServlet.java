package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/list")
@SuppressWarnings("serial")
public class MemberListServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Connection conn = null;		// DB 서버와의 연결 객체
		Statement stmt = null;		// sql문
		ResultSet rs = null;		// Select문의 결과
		
		try {
			// 메모리에 클래스 로딩
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:4306/studydb",	// JDBC url
					"study",								// id
					"study");								// password
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT mno, mname, email, cre_date" +
								" FROM members" + 
								" ORDER BY mno ASC");
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<html><head><title>회원 목록</title></head>");
			out.println("<body><h1>회원 목록</h1>");
			/* 신규회원 추가*/
			/* 
			 * href ='/add' : 절대경로
			 * 	localhost:9999/<contextRoot>/add
			 * 
			 * href = 'add' : 상대경로
			 * 	localhost:9999/<contextRoot>/member/add
			 * */
			out.println("<p><a href='add'>신규 회원</a></p>");
			while(rs.next()) {
				out.println(
						rs.getInt("mno") + ", " +
						rs.getString("mname") + ", " +
						rs.getString("email") + ", " +
						rs.getDate("cre_date") + "<br>"
						);
			}
			out.println("</body></html>");
		}catch(Exception e) {
			throw new ServletException(e);
		}finally {
			// 생성한 역순으로 닫아준다.
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) rs.close();} catch(Exception e) {}
			try {if(conn!=null) rs.close();} catch(Exception e) {}
		}
	}

}
