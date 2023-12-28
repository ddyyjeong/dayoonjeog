package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelect {
	public static void main(String[] args) {
	// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		  // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클에서 제공한 드라이브 클래스 입니다. 상주시켜줍니다.
		  // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
		  // 3. SQL문 준비 / 바인딩 / 실행
			//sql 문
			String sql = "SELECT 	b.BOOK_ID ,\r\n"
							+ "		b.TITLE ,\r\n"
							+ "		b.PUBS ,\r\n"
							+ "		b.PUB_DATE ,\r\n"
							+ "		b.AUTHOR_ID \r\n"
							+ "FROM BOOK b\r\n"
							+ "ORDER BY b.BOOK_ID ";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			//실행
			rs = pstmt.executeQuery(); //select
			
		  // 4.결과처리
			while(rs.next()) { //커서를 끝까지 옮기면서 가져오겠다.
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getString(3) + ", ");
				System.out.print(rs.getString(4) + ", ");
				System.out.print(rs.getString(5) + "\n");
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
	}
}