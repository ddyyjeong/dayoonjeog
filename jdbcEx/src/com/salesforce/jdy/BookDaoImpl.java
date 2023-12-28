package com.salesforce.jdy;

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
	public List<BookVo> search() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> list = new ArrayList<BookVo>();
		
		String keyword = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어를 입력하세요 : ");
		keyword = sc.nextLine();
		
		try {
			conn = getConnection();
			//sql
			String sql = "SELECT b.BOOK_ID , \r\n"
							+ "		b.TITLE , \r\n"
							+ "		b.PUBS  , \r\n"
							+ "		TO_CHAR(b.PUB_DATE , 'yyyymmdd') , \r\n"
							+ "		a.AUTHOR_NAME\r\n"
					+ " FROM BOOK b , AUTHOR a \r\n"
					+ " WHERE b.AUTHOR_ID = a.AUTHOR_ID \r\n"
					+ " AND b.TITLE || b.PUBS || a.AUTHOR_NAME  LIKE ? "
					+ " ORDER BY b.BOOK_ID ";
			//바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			//실행
			rs = pstmt.executeQuery();
			//결과처리
			
			while(rs.next()) {
				BookVo vo = new BookVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				list.add(vo);
			}
			
			sc.close();
		} catch(SQLException e ) {
			e.printStackTrace();
			System.out.println("error : " + e);
		} finally {
			//자원정리
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
}
