package com.seok.stm.reply.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seok.stm.commons.paging.Criteria;
import com.seok.stm.commons.paging.PageMaker;
import com.seok.stm.reply.domain.ReplyVO;
import com.seok.stm.reply.service.ReplyService;


@RestController //REST 방식을 쓰기위한 애너테이션
@RequestMapping("reply")
public class ReplyController {
	
	private final ReplyService replyService;
	
	@Inject
	public ReplyController(ReplyService replyService) {
		// TODO Auto-generated constructor stub
		this.replyService = replyService;
	}

	//댓글 등록처리 
	@RequestMapping(value="", method= RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO replyVO) {
		ResponseEntity<String> entity = null;
		try {
			replyService.addReply(replyVO);
			entity = new ResponseEntity<>("regSuccess",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 목록
	@RequestMapping(value="/all/{moiveNo}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("moiveNo") Integer moiveNo){
		ResponseEntity<List<ReplyVO>> entity = null;;
		
		try {
			entity = new ResponseEntity<>(replyService.getReplies(moiveNo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 수정
	@RequestMapping(value="{replyNo}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("replyNo") Integer replyNo, @RequestBody ReplyVO replyVO){
		ResponseEntity<String> entity = null;
		try {
			replyVO.setReplyNo(replyNo);
			replyService.modifyReply(replyVO);
			entity = new ResponseEntity<>("modSuccess",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 삭제
	@RequestMapping(value="/{replyNo}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("replyNo") Integer replyNo){
		ResponseEntity<String> entity = null;
		try {
			replyService.removeReply(replyNo);
			entity = new ResponseEntity<>("delSuccess",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/{moiveNo}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listPaging(@PathVariable("moiveNo") Integer moiveNo,
			@PathVariable("page") Integer page){
		ResponseEntity<Map<String,Object>> entity = null;
		
		try {
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			List<ReplyVO> replies = replyService.getRepliesPaging(moiveNo, criteria);
			int repliesCount = replyService.countReplies(moiveNo);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(repliesCount);
			Map<String,Object> map = new HashMap<>();
			map.put("replies", replies);
			map.put("pageMaker",pageMaker);
			
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
