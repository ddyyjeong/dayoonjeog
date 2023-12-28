package com.salesforce.jdbcdao.jdy;

public class AccountVo {
	private int seq_id;
	private int deposit;
	private int withdraw;
	private String tr_date;
	private int balance;
	
	public int getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(int seq_id) {
		this.seq_id = seq_id;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}
	public String getTr_date() {
		return tr_date;
	}
	public void setTr_date(String tr_date) {
		this.tr_date = tr_date;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public AccountVo() {
		super();
	}
	public AccountVo(int seq_id, int deposit, int withdraw, String tr_date, int balance) {
		super();
		this.seq_id = seq_id;
		this.deposit = deposit;
		this.withdraw = withdraw;
		this.tr_date = tr_date;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountVo [seq_id=" + seq_id + ", deposit=" + deposit + ", withdraw=" + withdraw + ", tr_date="
				+ tr_date + ", balance=" + balance + "]";
	}
	
}
