package com.seok.stm.movie.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seok.stm.commons.paging.SearchCriteria;
import com.seok.stm.movie.domain.MovieVO;

@Repository
public class MovieDAOImpl implements MovieDAO {
	
	private static final String NAMESPACE = "com.seok.stm.mappers.movie.MovieMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public MovieDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void create(MovieVO movieVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".create",movieVO);
	}

	@Override
	public MovieVO read(Integer movieNo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", movieNo);
	}

	@Override
	public void update(MovieVO movieVO) throws Exception {
		sqlSession.update(NAMESPACE + ".update",movieVO);
	}

	@Override
	public void delete(Integer movieNo) throws Exception {
		sqlSession.delete(NAMESPACE +".delete", movieNo);
	}

	@Override
	public List<MovieVO> listAll() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll");
	}

	@Override
	public List<MovieVO> listSearch(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE +".listSearch",searchCriteria);
	}

	@Override
	public int countSearchedMovies(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".countSearchedMovies", searchCriteria);
	}

	@Override
	public void updateHit(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE +".updateHit", movieNo);
	}

	@Override
	public List<String> imageName(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".imageName",movieNo);
	}

}
