package com.seok.stm.movie.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.seok.stm.commons.paging.SearchCriteria;
import com.seok.stm.movie.domain.MovieVO;
import com.seok.stm.movie.persistence.MovieDAO;
import com.seok.stm.upload.persistence.ImageFileDAO;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieDAO movieDAO;
	private final ImageFileDAO imageFileDAO; 
	
	
	@Inject
	public MovieServiceImpl(MovieDAO movieDAO, ImageFileDAO imageFileDAO) {
		// TODO Auto-generated constructor stub
		this.movieDAO = movieDAO;
		this.imageFileDAO = imageFileDAO;
	}
	
	@Transactional
	@Override
	public void create(MovieVO movieVO) throws Exception {
		// TODO Auto-generated method stub
		movieDAO.create(movieVO);
		String[] files = movieVO.getFiles();
		
		if(files == null)
			return;
		
		// 게시글 첨부파일 입력처리
		for(String fileName : files)
			imageFileDAO.addFile(fileName);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public MovieVO read(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return movieDAO.read(movieNo);
	}
	
	@Transactional
	@Override
	public void update(MovieVO movieVO) throws Exception {
		// TODO Auto-generated method stub
		Integer movieNo = movieVO.getMovieNo();
		String[] files = movieVO.getFiles();
		
		movieDAO.update(movieVO);
		imageFileDAO.deleteFiles(movieNo);
		
		if(files == null)
			return;
		for(String fileName : files)
			imageFileDAO.replaceFile(fileName, movieNo);
	}
	
	@Transactional
	@Override
	public void delete(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		imageFileDAO.deleteFiles(movieNo);
		movieDAO.delete(movieNo);
	}

	@Override
	public List<MovieVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return movieDAO.listAll();
	}

	@Override
	public List<MovieVO> listSearch(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return movieDAO.listSearch(searchCriteria);
	}

	@Override
	public int countSearchedMovies(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return movieDAO.countSearchedMovies(searchCriteria);
	}

	@Override
	public void updateHit(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		movieDAO.updateHit(movieNo);
	}

	@Override
	public List<String> imageName(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return movieDAO.imageName(movieNo);
	}


}
