package com.seok.stm.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.seok.stm.commons.paging.Criteria;
import com.seok.stm.reply.domain.ReplyVO;
import com.seok.stm.reply.persistence.ReplyDAO;


@Service
public class ReplyServiceImpl implements ReplyService {

	private ReplyDAO replyDAO;
	
	@Inject
	public ReplyServiceImpl(ReplyDAO replyDAO) {
		// TODO Auto-generated constructor stub
		this.replyDAO = replyDAO;
	}

	@Override
	public List<ReplyVO> getReplies(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.list(movieNo);
	}

	@Override
	public void addReply(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.create(replyVO);
	}

	@Override
	public void modifyReply(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.update(replyVO);
	}

	@Override
	public void removeReply(Integer replyNo) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.delete(replyNo);
	}

	@Override
	public List<ReplyVO> getRepliesPaging(Integer movieNo, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.listPaging(movieNo, criteria);
	}

	@Override
	public int countReplies(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.countReply(movieNo);
	}
	

}
