package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Member;

@Component("memberDao")
public class MysqlMemberDao implements MemberDao {
	
	
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
	
	// apache 라이브러리에서 제공하는 DataSource내에 커넥션풀이 포함되어 있다. 
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
//	우리가 만든 커넥션풀을 사용하지 않는다.	
//	DBConnectionPool connPool;
//	public void setDBConnectionPool(DBConnectionPool connPool) {
//		this.connPool = connPool;
//	}
		
	
	// MemberListServlet에서 필요
	public List<Member> selectList() throws Exception{
		Connection connection = null;	// 추가
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 커넥션풀에 객체를 빌려온다.
			// connection = this.connPool.getConnection();		// 추가
			connection = this.ds.getConnection();
			
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
			// if(connection != null) connPool.returnConnection(connection);
			// DataSource가 제공하는 connection의 close()는 서버와의 단절이 아니라
			// 커넥션풀로의 반환을 의미한다.
			try{if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	// MemberAddServlet에서 필요
	public int insert(Member member) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			// 커넥션풀에 객체를 빌려온다.
			// connection = this.connPool.getConnection();		// 추가
			connection = this.ds.getConnection();
			
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
			// DataSource가 제공하는 connection의 close()는 서버와의 단절이 아니라
			// 커넥션풀로의 반환을 의미한다.
			try{if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	// MemberDeleteServlet에서 필요
	public int delete(int no) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			// 커넥션풀에 객체를 빌려온다.
			// connection = this.connPool.getConnection();		// 추가
			connection = this.ds.getConnection();
			
			stmt = connection.prepareStatement(strDelete);
			stmt.setInt(1, no);
			return stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			// DataSource가 제공하는 connection의 close()는 서버와의 단절이 아니라
			// 커넥션풀로의 반환을 의미한다.
			try{if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	// MemberUpdateServlet에서 get 요청시 필요
	public Member selectOne(int no) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 커넥션풀에 객체를 빌려온다.
			// connection = this.connPool.getConnection();		// 추가
			connection = this.ds.getConnection();
			
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
			// DataSource가 제공하는 connection의 close()는 서버와의 단절이 아니라
			// 커넥션풀로의 반환을 의미한다.
			try{if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	// MemberUpdateServlet에서 post 요청시 필요필요
	public int update(Member member) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			// 커넥션풀에 객체를 빌려온다.
			// connection = this.connPool.getConnection();		// 추가
			connection = this.ds.getConnection();
						
			stmt = connection.prepareStatement(strUpdate);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			
			return stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			// DataSource가 제공하는 connection의 close()는 서버와의 단절이 아니라
			// 커넥션풀로의 반환을 의미한다.
			try{if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	// LogInServlet에 필요
	public Member exist(String email, String password) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 커넥션풀에 객체를 빌려온다.
			// connection = this.connPool.getConnection();		// 추가
			connection = this.ds.getConnection();
						
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
			e.printStackTrace();
			throw e;
		}finally {
			try {if(rs!=null)rs.close(); }catch(Exception e) {}
			try {if(stmt!=null)stmt.close();} catch(Exception e) {}
			// DataSource가 제공하는 connection의 close()는 서버와의 단절이 아니라
			// 커넥션풀로의 반환을 의미한다.
			try{if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
}
