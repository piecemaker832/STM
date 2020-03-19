package com.seok.stm.reply.persistence;

import java.util.List;

import com.seok.stm.commons.paging.Criteria;
import com.seok.stm.reply.domain.ReplyVO;

public interface ReplyDAO {
	List<ReplyVO> list(Integer movieNo) throws Exception;
	void create(ReplyVO replyVO) throws Exception;
	void update(ReplyVO replyVO) throws Exception;
	void delete(Integer replyNo) throws Exception;
	List<ReplyVO> listPaging(Integer movieNo, Criteria criteria) throws Exception;
	int countReply(Integer movieNo) throws Exception;
}
