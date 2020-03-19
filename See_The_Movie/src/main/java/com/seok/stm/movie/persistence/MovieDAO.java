package com.seok.stm.movie.persistence;

import java.util.List;

import com.seok.stm.commons.paging.SearchCriteria;
import com.seok.stm.movie.domain.MovieVO;

public interface MovieDAO {
	void create(MovieVO movieVO) throws Exception; // 글작성
	MovieVO read(Integer movieNo) throws Exception; // 글조회
	void update(MovieVO movieVO) throws Exception; // 글수정
	void delete(Integer movieNo) throws Exception; // 글삭제
	List<MovieVO> listAll() throws Exception; //게시글 리스트 출력
	List<MovieVO> listSearch(SearchCriteria searchCriteria) throws Exception;
	int countSearchedMovies(SearchCriteria searchCriteria) throws Exception;
	void updateHit(Integer movieNo) throws Exception;
	List<String> imageName(Integer movieNo) throws Exception; // 조회시 이미지 출력
}
