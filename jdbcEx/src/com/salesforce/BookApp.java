package com.salesforce;

import java.util.ArrayList;
import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		BookDao dao = new BookDaoImpl();

		// 입력하기
		BookVo vo = new BookVo(1, "역행자", "이스트", "20231122", 2); // 제목,출판사,yyyymmdd,저자번호
		dao.insert(vo);

		// 가져오기
		List<BookVo> list = new ArrayList<BookVo>();
		list = dao.getList();
		
		for (BookVo vo3 : list) {
			System.out.println(vo3.getBook_id() + "\t" + vo3.getTitle() + "\t" + vo3.getPubs() + "\t" + vo3.getPub_date() + "\t" + vo3.getAuthor_id());
		};

		// 삭제하기
		Long BookId = new Long(46);
		dao.delete(BookId);

		// 업데이트하기
		BookVo vo2 = new BookVo(44, "", "이지스", "", 1);
		dao.update(vo2);

		// 검색하기
		dao.search();

	}

}
