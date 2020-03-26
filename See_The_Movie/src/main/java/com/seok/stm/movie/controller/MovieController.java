package com.seok.stm.movie.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seok.stm.commons.paging.PageMaker;
import com.seok.stm.commons.paging.SearchCriteria;
import com.seok.stm.movie.domain.MovieVO;
import com.seok.stm.movie.service.MovieService;



@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	private final MovieService movieService;
	
	@Inject
	public MovieController(MovieService movieService) {
		// TODO Auto-generated constructor stub
		this.movieService = movieService;
	}
	
	// 글쓰기페이지 이동
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String writeGET() {
		logger.info("write GET");
		
		return "/movie/write";
	}
	
	// 글쓰기 처리
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String writePOST(MovieVO movieVO, RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("write POST");
		movieVO.setMovieSummary(movieVO.getMovieSummary().replace("\r\n", "<br>"));
		movieService.create(movieVO);
		redirectAttributes.addFlashAttribute("msg","regSuccess");
		
		return "redirect:/movie/listall";
	}
	
	// 게시글 목록
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		logger.info("listGET");
		
		model.addAttribute("movies", movieService.listAll());
		return "/movie/list";
	}
	
	// 게시글(페이징&검색)목록
	@RequestMapping(value="/listall", method = RequestMethod.GET)
	public String pagingList(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model) throws Exception {
		logger.info("listGET");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(movieService.countSearchedMovies(searchCriteria));
		
		model.addAttribute("movies", movieService.listSearch(searchCriteria));
		model.addAttribute("pageMaker",pageMaker);
		return "/movie/list";
	}
	
	// 게시글 조회
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public String read(@RequestParam("movieNo") int movieNo,
			@ModelAttribute("searchCriteria")SearchCriteria searchCriteria,
			Model model) throws Exception {
		logger.info("readGET");
		
		movieService.updateHit(movieNo);
		model.addAttribute("movies", movieService.read(movieNo));
		model.addAttribute("image",movieService.imageName(movieNo));
		System.out.println(movieService.imageName(movieNo));
		return "/movie/read";
	}
	
	// 수정페이지 이동
	@RequestMapping(value="/update" , method = RequestMethod.GET)
	public String updateGET(@RequestParam("movieNo") int movieNo,
			@ModelAttribute("searchCriteria")SearchCriteria searchCriteria,
			Model model) throws Exception {
		logger.info("updateGET");
		
		model.addAttribute("movies",movieService.read(movieNo));
		
		return "/movie/update";
	}
	
	// 수정처리
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updatePOST(MovieVO movieVO, 
							SearchCriteria searchCriteria,
							RedirectAttributes redirectAttributes) throws Exception {
		logger.info("updatePOST");
		
		movieService.update(movieVO);
		redirectAttributes.addAttribute("page",searchCriteria.getPage());
		redirectAttributes.addAttribute("perPageNum",searchCriteria.getPerPageNum());
		redirectAttributes.addAttribute("searchType",searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword",searchCriteria.getKeyword());
		redirectAttributes.addFlashAttribute("msg","updateSuccess");
		return "redirect:/movie/listall";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam("movieNo") int movieNo,
						SearchCriteria searchCriteria,
						RedirectAttributes redirectAttributes) throws Exception {
		logger.info("delete");
		
		movieService.delete(movieNo);
		redirectAttributes.addAttribute("page",searchCriteria.getPage());
		redirectAttributes.addAttribute("perPageNum",searchCriteria.getPerPageNum());
		redirectAttributes.addAttribute("searchType",searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword",searchCriteria.getKeyword());
		redirectAttributes.addFlashAttribute("msg","deleteSuccess");
		
		return "redirect:/movie/listall";
	}
}
