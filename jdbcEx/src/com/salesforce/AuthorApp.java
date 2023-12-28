package com.salesforce;

public class AuthorApp {

	public static void main(String[] args) {
		AuthorDao dao = new AuthorDaoImpl();
		
		//데이터 추가
		AuthorVO vo = new AuthorVO(1,"전두광","캐릭터");		
		dao.insert(vo);
		
		//기존 데이터 수정
		AuthorVO vo2 = new AuthorVO(27, "전두광", "모티브 : 전두환");
		dao.update(vo2);
		
		//기존 데이터 삭제
		Long authorId = new Long(28); //대문자로 시작한 레퍼 클래스
		dao.delete(authorId);
		
		//전체 데이터 조회
		int count = 0;
		count = dao.getList();
		System.out.println("전체건수 : " + count );
		
	}

}
