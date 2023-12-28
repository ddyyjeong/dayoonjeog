package com.salesforce;

import java.util.ArrayList;
import java.util.List;

public class AuthorAppArrayList {

	public static void main(String[] args) {
		AuthorDaoImplArrayList dao = new AuthorDaoImplArrayList();
		List<AuthorVO> list = new ArrayList<AuthorVO>();

		int count = 0;
		// 데이터 추가
		AuthorVO vo = new AuthorVO(1, "이태신", "캐릭터");
		count = dao.insert(vo);
		System.out.println("입력 데이터 건수 : " + count);

		// 기존 데이터 수정
		AuthorVO vo2 = new AuthorVO(42, "이태신", "모티브 : 장태완");
		count = dao.update(vo2);
		System.out.println("수정 데이터 건수 : " + count);

		// 기존 데이터 삭제
		Long authorId = new Long(26); // 대문자로 시작한 레퍼 클래스(단순 타입 아님)
		count = dao.delete(authorId);
		System.out.println("삭제 데이터 건수 : " + count);

		// 전체 데이터 조회
		list = dao.getList();

		for (AuthorVO vo3 : list) {
			System.out.println(vo3.getAuthor_id() + "\t" + vo3.getAuthor_name() + "\t" + vo3.getAuthor_desc());
		}
		System.out.println("전체 데이터 건수 : " + list.size());

	}

}
