package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImplArrayList2 implements AuthorDaoArrayLIst {
	//커넥션 만들기 전입니다.

	@Override
	public int insert(AuthorVO vo) {

		System.out.println("AuthorDaoImpl.insert(vo) --> " + vo);

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 오라클에서 제공한 드라이브 클래스 입니다. 상주시켜줍니다.
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = "INSERT INTO AUTHOR a \r\n" + "VALUES (seq_author_id.nextval, ?, ?)";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getAuthor_name());
			pstmt.setString(2, vo.getAuthor_desc());

			// 실행
			// rs = pstmt.executeQuery(); 지금은 업데이트로 해야함
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건이 반영되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return count;
	}

	@Override
	public List<AuthorVO> getList() {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AuthorVO> list = new ArrayList<AuthorVO>();
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 오라클에서 제공한 드라이브 클래스 입니다. 상주시켜줍니다.
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = "SELECT a.*\r\n" + " FROM AUTHOR a\r\n" + " ORDER BY a.AUTHOR_ID";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			// 실행
			rs = pstmt.executeQuery(); // select

			// 4.결과처리
			while (rs.next()) { // 커서를 끝까지 옮기면서 가져오겠다.
				AuthorVO vo = new AuthorVO(rs.getInt(1), rs.getString(2), rs.getString(3)); //AuthorVO 생성자, 루프 돌 때마다 만들어서
				list.add(vo); //vo에 담음
//				System.out.print(rs.getInt(1) + ", ");
//				System.out.print(rs.getString(2) + ", ");
//				System.out.print(rs.getString(3) + " \n");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return list;
	}

	@Override
	public int delete(Long authorId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 오라클에서 제공한 드라이브 클래스 입니다. 상주시켜줍니다.
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = " DELETE FROM AUTHOR a \r\n" + " WHERE a.author_id = ? ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, authorId);
			// 실행
			int count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.print(count + "건이 삭제되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return 0;
	}

	@Override
	public int update(AuthorVO vo) {

		System.out.println("AuthorDaoImpl.insert(vo) --> " + vo);

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 오라클에서 제공한 드라이브 클래스 입니다. 상주시켜줍니다.
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = "UPDATE AUTHOR a \r\n" + " SET a.AUTHOR_DESC = ? \r\n" + " WHERE a.AUTHOR_ID = ? ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getAuthor_desc());
			pstmt.setInt(2, vo.getAuthor_id());
			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건이 반영되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return count;
	}

}
