package com.salesforce.jdbcdao.jdy;

import java.util.Scanner;

public class AccountApp {

	public static void main(String[] args) {
		boolean run = true;
		AccountDao dao = new AccountDaoImpl();
		Scanner sc = new Scanner(System.in);
		while(run) {
			System.out.println("메뉴를 선택하세요.");
			System.out.println("-----------------------------------------------------------");
			System.out.println(" 1.입금  |  2.출금  |  3.잔액조회  |  4.거래일 조회  |  5.기간별 조회 ");
			System.out.println("-----------------------------------------------------------");
			System.out.print(">");
			int menu = sc.nextInt();
			System.out.println(menu);
			
			switch (menu) {
			
			case 1 : case 2 :
				dao.insertTradeInfo(null, menu);
				
				break;
			case 3:
				dao.getBalance();
				
				break;
			case 4:
				System.out.println("조회할 거래날짜를 입력하세요(yyyymmdd)");
				String TradingDate = sc.nextLine();
				dao.getList(TradingDate);
				
				break;
			case 5:
				dao.getList(null, null);
				
				break;
			default:
				System.out.println("잘못 누르셨습니다.");
				
				break;

			}

		}
		sc.close();

	}

}
