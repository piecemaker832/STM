package com.seok.stm.upload.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ImageFileDAOImpl implements ImageFileDAO {

	private static final String NAMESPACE = "com.seok.stm.mappers.upload.ImageFileMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public ImageFileDAOImpl(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void addFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DAO : " +fileName);
		sqlSession.insert(NAMESPACE + ".addFile",fileName);
	}

	@Override
	public List<String> getImageFiles(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".getImageFiles",movieNo);
	}

	@Override
	public void deleteFiles(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(NAMESPACE + ".deleteFiles",movieNo);
	}

	@Override
	public void deleteFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(NAMESPACE + ".deleteFile",fileName);
	}

	@Override
	public void replaceFile(String fileName, Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("fileName", fileName);
		paramMap.put("boardNo", movieNo);
		sqlSession.insert(NAMESPACE + ".replaceFile", paramMap);
	}

	@Override
	public void updateFileCnt(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".updateFileCnt",movieNo);
	}


}
