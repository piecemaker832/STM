package com.seok.stm.movie.persistence;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.seok.stm.movie.domain.MovieVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MovieDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(MovieDAOTest.class);
	
	@Inject
	private MovieDAO movieDAO;
	
	@Test
	public void testCreate() throws Exception {
		for( int i = 1 ; i <= 1000 ; i++) {
			MovieVO movieVo = new MovieVO();
			movieVo.setMovieTitle(i+"번쨰 글 제목입니다 . . .");
			movieVo.setMovieSummary(i+"번째 글 내용입니다.");
			movieVo.setMovieRating(5);
			movieVo.setMovieWriter("test");
			
			movieDAO.create(movieVo);
		}
	}

}
