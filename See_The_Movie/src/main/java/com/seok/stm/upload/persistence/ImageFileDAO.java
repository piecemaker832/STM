package com.seok.stm.upload.persistence;

import java.util.List;

public interface ImageFileDAO {
	
	// 이미지 파일 추가
	void addFile(String fullName) throws Exception;
	// list 페이지 이미지파일  출력
	List<String> getImageFiles(Integer moiveNo) throws Exception;
	// 이미지 파일 삭제
	void deleteFiles(Integer moiveNo) throws Exception;
	
	// 파일 삭제/수정/개수 갱신
	void deleteFile(String fileName) throws Exception;
	void replaceFile(String fileName, Integer moiveNo) throws Exception;
	void updateFileCnt(Integer moiveNo) throws Exception;
	
}
