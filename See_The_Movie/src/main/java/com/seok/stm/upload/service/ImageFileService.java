package com.seok.stm.upload.service;

import java.util.List;

public interface ImageFileService {
	
	List<String> getImageFiles(Integer moviesNo) throws Exception;
	
	// 파일 삭제
    void deleteFile(String fileName, Integer moviesNo) throws Exception;
}
