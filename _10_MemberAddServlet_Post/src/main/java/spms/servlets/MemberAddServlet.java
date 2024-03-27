package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet{
	
	// get : 화면에 정보나 페이지를 보여주는 역할
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>회원등록</title></head>");
		out.println("<body><h1>회원등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("이름 : <input type='text' name='name'><br>");
		out.println("이메일 : <input type='text' name='email'><br>");
		out.println("암호 : <input type='text' name='password'><br>");
		out.println("<input type='submit' value='추가'>");
		out.println("<input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
	}
	
	// post : 주로 정보를 전달받아서 db에 저장하는 역할
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 브라우저의 request에 한글이 포함되어 있을 경우 get요청은 소스에 하지 않고 tomcat 의 server.xml에 해야함
		// post 요청은 doPost()의 값을 꺼내기 전에 설정한다.
		
		// 파라미터를 꺼내기 전에 적용해야함
		// 이 설정을 추가해야 한글이 꺠지지 않고 저장됨		
		req.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		/* 
		 * Statement
		 * - 질의할 때마다 sql을 컴파일한다.
		 * - 입력 매개변수가 여러 개 필요할 때 문자열 결합연산자인 +를 이용해서 해야한다.
		 * - 전송 직전에 sql문을 입력받고, 컴파일 후 서버로 전송
		 * 
		 * PrepareStatement
		 * - sql 문을 미리 입력하여 컴파일한 상태에서 객체를 받는다.
		 * - 만약에 sql 문 구조가 변경되지 않고, 파라미터 값만 바뀌는 경우 Statement 보다 훨씬 빠르다
		 * - 입력 매개변수가 여러개 필요할 때 ?로 sql 의 파라미터를 표시하고, 나중에 전달하므로 편하다.
		 * - Statement < PrepareStatement를 사용한다.
		 * - 객체를 얻기 전에 sql문 입력받음
		 * */
		PreparedStatement stmt = null;
//		ResultSet rs = null; select문이 아닐때는 ResultSet가 딱히 필요하지 않음
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:4306/studydb",
					"study",
					"study"
					);
			stmt = conn.prepareStatement(
					"INSERT INTO members(email,pwd,mname,cre_date,mod_date)" +
					" VALUES(?,?,?,NOW(),NOW())"
					);
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			stmt.setString(3, req.getParameter("name"));
			stmt.executeUpdate();
			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원등록결과</title></head>");
			out.println("<body>");
			out.println("<p>등록 성공입니다.</p>");
			out.println("</body></html>");
		}catch(Exception e) {
			throw new ServletException(e);
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e){}
			try {if(conn!=null) stmt.close();} catch(Exception e){}
		}
	}
}









