package com.salesforce;

import java.util.List;

public interface BookDao {
	public int insert(BookVo vo); //입력하기
	public List<BookVo> getList(); //전체가져오기
	public int delete(Long bookId); //삭제하기
	public int update(BookVo vo); //업데이트하기
	public String search(); //검색하기
}
