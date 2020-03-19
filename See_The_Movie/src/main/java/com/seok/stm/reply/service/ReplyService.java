package com.seok.stm.reply.service;

import java.util.List;

import com.seok.stm.commons.paging.Criteria;
import com.seok.stm.reply.domain.ReplyVO;


public interface ReplyService {
	List<ReplyVO> getReplies(Integer movieNo) throws Exception;
	void addReply(ReplyVO replyVO) throws Exception;
	void modifyReply(ReplyVO replyVO) throws Exception;
	void removeReply(Integer movieNo) throws Exception;
	List<ReplyVO> getRepliesPaging(Integer boardNo, Criteria criteria) throws Exception;
	int countReplies(Integer movieNo) throws Exception;
}
