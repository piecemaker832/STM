package com.seok.stm.upload.service;

import java.util.List;

public interface ImageFileService {
	
	List<String> getImageFiles(Integer moviesNo) throws Exception;
	
	// ���� ����
    void deleteFile(String fileName, Integer moviesNo) throws Exception;
}
