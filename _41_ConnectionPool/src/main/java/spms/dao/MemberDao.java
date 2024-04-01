package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import spms.util.DBConnectionPool;
import spms.vo.Member;

/* Dao (Data Access Object)
 * 데이터베이스를 연결하여 데이터 입출력을 담당하는 클래스
 * 이 클래스로 만들어진 오브젝트를 Dao라고 부른다.
 * */
public class MemberDao {
	
	
	private String strSelectList = "SELECT mno,mname,email,cre_date FROM members ORDER BY mno ASC";
	private String steInsert = 
			"INSERT INTO members(email,pwd,mname,cre_date,mod_date) VALUES(?,?,?,NOW(),NOW())";
	private String strDelete =
			"DELETE FROM members WHERE mno=?";
	private String strSelectOne =
			"SELECT mno,email,mname,cre_date FROM members WHERE mno=?";
	private String strUpdate=
			"UPDATE members SET email=?, mname=?, mod_date=NOW() WHERE mno=?";
	private String strExist=
			"SELECT mname, email FROM members WHERE email=? AND pwd=?";
	
	DBConnectionPool connPool;
	
	public void setDBConnectionPool(DBConnectionPool connPool) {
		this.connPool = connPool;
	}
	
	/*
	 * Connection 객체 1개로 여러 메서드가 각각의 서블릿에서 호출되면
	 * rollback시 다른 명령에도 영향을 주어서 취소가 되므로
	 * 이제 ConnectionPool을 사용해서 독립적인 명령 처리가 되도록 한다.
	 
	Connection connection;
	public void setConnection(Connection connection) {
		this.connection= connection;
	}
	*/
	
	// MemberListServlet에서 필요
	public List<Member> selectList() throws Exception{
		Connection connection = null;	// 추가
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 커넥션풀에 객체를 빌려온다.
			connection = this.connPool.getConnection();		// 추가
			
			stmt = connection.prepareStatement(strSelectList);
			rs = stmt.executeQuery();
			
			List<Member> members = new ArrayList<>();
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("mno"))
							.setName(rs.getString("mname"))
							.setCreatedDate(rs.getDate("cre_date"))
							);
			}
			
			return members;
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(rs!=null)rs.close(); }catch(Exception e) {}
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			// 커넥션풀에서 빌려온 Connection 객체를 반납한다.
			if(connection != null) connPool.returnConnection(connection);
		}
	}
	
	// MemberAddServlet에서 필요
	public int insert(Member member) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = this.connPool.getConnection();
			
			stmt = connection.prepareStatement(steInsert);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			
			/*
				DB에서 이 명령이 적용된 row의 갯수를 반환한다.
				1개 입력되면 1을 리턴(보통 1을 리턴)
				1이면 입력 성공,
				0이면 입력 안됨
			*/
			int count = stmt.executeUpdate();
			return count;
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			
			if(connection != null) connPool.returnConnection(connection);
		}
	}
	
	// MemberDeleteServlet에서 필요
	public int delete(int no) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = this.connPool.getConnection();
			
			stmt = connection.prepareStatement(strDelete);
			stmt.setInt(1, no);
			return stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			if(connection != null) connPool.returnConnection(connection);
		}
	}
	
	// MemberUpdateServlet에서 get 요청시 필요
	public Member selectOne(int no) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.connPool.getConnection();
			
			stmt = connection.prepareStatement(strSelectOne);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return new Member()
						.setNo(rs.getInt("mno"))
						.setEmail(rs.getString("email"))
						.setName(rs.getString("mname"))
						.setCreatedDate(rs.getDate("cre_date"));
			}else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			if(connection != null) connPool.returnConnection(connection);
		}
	}
	
	// MemberUpdateServlet에서 post 요청시 필요필요
	public int update(Member member) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = this.connPool.getConnection();
			stmt = connection.prepareStatement(strUpdate);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			
			return stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			if(connection != null) connPool.returnConnection(connection);
		}
	}
	
	// LogInServlet에 필요
	public Member exist(String email, String password) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.connPool.getConnection();
			stmt = connection.prepareStatement(strExist);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Member()
						.setName(rs.getString("mname"))
						.setEmail(rs.getString("email"));
			}else {
				return null;
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(rs!=null)rs.close(); }catch(Exception e) {}
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			if(connection != null) connPool.returnConnection(connection);
		}
	}
}
