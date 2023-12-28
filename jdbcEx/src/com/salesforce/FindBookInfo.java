package com.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FindBookInfo {
	public static void main(String[] args) {
	// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("찾고자 하는 저자의 이름을 입력 하세요 >> ");
		String inputName = sc.nextLine() ;
		try {
		  // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클에서 제공한 드라이브 클래스 입니다. 상주시켜줍니다.
		  // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
		  // 3. SQL문 준비 / 바인딩 / 실행
			//sql 문
			String sql = "SELECT a.AUTHOR_ID ,"
								+ " a.AUTHOR_NAME ,"
								+ " b.BOOK_ID ,"
								+ " b.TITLE \r\n"
					+ "FROM AUTHOR a , BOOK b \r\n"
					+ "WHERE a.AUTHOR_ID = b.AUTHOR_ID \r\n"
					+ "AND a.AUTHOR_NAME = ? \r\n"
					+ "ORDER BY a.AUTHOR_ID";
			// 바인딩
			pstmt = conn.prepareStatement(sql);
			//select
			pstmt.setString(1, "%" + inputName + "%");
			//실행
			rs = pstmt.executeQuery(); //select
			
		  // 4.결과처리
			while(rs.next()) { //커서를 끝까지 옮기면서 가져오겠다.
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getInt(3) + ", ");
				System.out.print(rs.getString(4) + " \n");
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
		} sc.close();
	}
}