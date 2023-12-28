package com.salesforce.jdy;

import java.util.ArrayList;
import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		BookDao dao = new BookDaoImpl();
		List<BookVo> list = new ArrayList<BookVo>();

		list = dao.search();

		System.out.println(list.size() + "건의 결과가 있습니다.");
		for (BookVo vo : list) {
			System.out.println(vo.getBook_id() + "\t" + 
							   vo.getTitle() + "\t" + 
							   vo.getPubs() + "\t" +
							   vo.getPub_date() + "\t" + 
							   vo.getAuthor_name());
		}
	}
}
