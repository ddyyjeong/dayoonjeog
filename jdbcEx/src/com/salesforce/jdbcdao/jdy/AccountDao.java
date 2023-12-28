package com.salesforce.jdbcdao.jdy;

import java.util.List;

public interface AccountDao {
	public AccountVo getBalance();

	public List<AccountVo> getListAll();

	public List<AccountVo> getList(String searchStartDate, String searchEndDate);

	public List<AccountVo> getList(String TradingDate);

	public int insertTradeInfo(String menu, int money);
}
