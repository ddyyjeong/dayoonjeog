package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindBookInfo_eun {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql 문
			String sql = "";			
//			sql = " select * from book where book_id = ? ";
			
			sql = "SELECT a.*, "
						+ "b.AUTHOR_NAME "
				+ "FROM BOOK a " 
				+ "INNER JOIN AUTHOR b " 
				+ "ON a.AUTHOR_ID = b.AUTHOR_ID " 
				+ "WHERE b.AUTHOR_NAME = ?";
			
			// 바인딩
			pstmt = conn.prepareStatement(sql);
//          select
			pstmt.setString(1, "이문열");
			
			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
//			System.out.print(count + "건이 반영 완료 되었습니다.");
			while(rs.next()) {
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getString(3) + ", ");
				System.out.print(rs.getString(4) + ", ");
				System.out.print(rs.getInt(5) + ", ");
				System.out.println(rs.getString(6));
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if(rs != null) {
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
