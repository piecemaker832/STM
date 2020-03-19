package com.seok.stm.movie.persistence;

import java.util.List;

import com.seok.stm.commons.paging.SearchCriteria;
import com.seok.stm.movie.domain.MovieVO;

public interface MovieDAO {
	void create(MovieVO movieVO) throws Exception; // ���ۼ�
	MovieVO read(Integer movieNo) throws Exception; // ����ȸ
	void update(MovieVO movieVO) throws Exception; // �ۼ���
	void delete(Integer movieNo) throws Exception; // �ۻ���
	List<MovieVO> listAll() throws Exception; //�Խñ� ����Ʈ ���
	List<MovieVO> listSearch(SearchCriteria searchCriteria) throws Exception;
	int countSearchedMovies(SearchCriteria searchCriteria) throws Exception;
	void updateHit(Integer movieNo) throws Exception;
	List<String> imageName(Integer movieNo) throws Exception; // ��ȸ�� �̹��� ���
}
