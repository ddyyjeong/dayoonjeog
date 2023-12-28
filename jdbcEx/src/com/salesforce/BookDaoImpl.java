package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDaoImpl implements BookDao {
	// 커넥션
	private Connection getConnection() {
		Connection conn = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 오라클에서 제공한 드라이브 클래스 입니다. 상주시켜줍니다.
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error:" + e);
		}
		return conn;
	} 

	@Override
	public int insert(BookVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			// sql
			String sql = " INSERT	INTO BOOK b\r\n" + " VALUES (seq_book_id.nextval, \r\n" + "			? ,\r\n"
					+ "			? , \r\n" + "			to_date( ? , 'YYYYMMDD HH24:MI:SS'), \r\n" + "			? )";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPub_date());
			pstmt.setInt(4, vo.getAuthor_id());
			// 실행
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error:" + e);

		} finally {
			// 5. 자원정리
			try {
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
	public List<BookVo> getList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<BookVo> list = new ArrayList<BookVo>();
		ResultSet rs = null;

		try {
			conn = getConnection();
			// sql
			String sql = "SELECT b.* \r\n" + " FROM book b \r\n" + " ORDER BY b.BOOK_ID ";

			// 바인딩
			pstmt = conn.prepareStatement(sql);
			// 실행
			rs = pstmt.executeQuery(); // select

			while (rs.next()) {
				BookVo vo;
				vo = new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int delete(Long bookId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			// sql
			String sql = " DELETE FROM BOOK b \r\n" + " WHERE book_id = ? ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bookId);
			// 실행
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error:" + e);

		} finally {
			// 5. 자원정리
			try {
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
	public int update(BookVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			// sql
			String sql = " UPDATE BOOK \r\n" + "SET PUBS = ? \r\n" + "WHERE BOOK_ID =? ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPubs());
			pstmt.setInt(2, vo.getAuthor_id());
			// 실행
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error:" + e);

		} finally {
			// 5. 자원정리
			try {
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
	public String search() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String keyword = null;
		Scanner sc = new Scanner(System.in);

		System.out.println("검색할 키워드를 입력하세요.");
		keyword = sc.nextLine();

		try {
			conn = getConnection();
			// sql
			String sql = "SELECT * \r\n"
					+ "FROM BOOK b , AUTHOR a \r\n"
					+ "WHERE b.AUTHOR_ID = a.AUTHOR_ID "
					+ " AND ( b.TITLE LIKE ? \r\n"
					+ " OR b.PUBS LIKE ? ) ";

			// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			// 실행
			rs = pstmt.executeQuery(); // select

			// 4.결과처리
			while (rs.next()) { // 커서를 끝까지 옮기면서 가져오겠다.
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getString(3) + ", ");
				System.out.print(rs.getString(4) + ", ");
				System.out.print(rs.getInt(5) + " \n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();
		return null ;
	}

}
