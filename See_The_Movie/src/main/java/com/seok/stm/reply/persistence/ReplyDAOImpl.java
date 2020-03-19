package com.seok.stm.reply.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.seok.stm.commons.paging.Criteria;
import com.seok.stm.reply.domain.ReplyVO;


@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	private static final String NAMESPACE = "com.seok.stm.mappers.reply.ReplyMapper";
	
	private SqlSession sqlSession;
	
	@Inject
	public ReplyDAOImpl(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<ReplyVO> list(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE +"list",movieNo);
	}

	@Override
	public void create(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE+".create",replyVO);
	}

	@Override
	public void update(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE+".update",replyVO);
	}

	@Override
	public void delete(Integer replyNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(NAMESPACE+".delete",replyNo);
	}

	@Override
	public List<ReplyVO> listPaging(Integer movieNo, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("movieNo", movieNo);
		paramMap.put("criteria", criteria);
		
		return sqlSession.selectList(NAMESPACE+".listPaging",paramMap);
	}

	@Override
	public int countReply(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+".countReply",movieNo);
	}

}
