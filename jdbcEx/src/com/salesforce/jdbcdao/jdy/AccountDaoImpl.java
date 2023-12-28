package com.salesforce.jdbcdao.jdy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
	
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
	public AccountVo getBalance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountVo> getListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountVo> getList(String searchStartDate, String searchEndDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountVo> getList(String TradingDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertTradeInfo(String menu, int money) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
