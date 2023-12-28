package com.salesforce;

public interface AuthorDao {
	public int insert(AuthorVO vo); //입력하기, 인터페이스니까 추상메소드가 된 것
//	public void getList(); //전체가져오기
	public int getList(); //전체 건수 세기 위해서 반환 타입 추가
	public int delete(Long authorId); //삭제하기, 결과는 1or0
	public int update(AuthorVO vo); //업데이트하기
}
