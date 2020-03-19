package com.seok.stm.upload.persistence;

import java.util.List;

public interface ImageFileDAO {
	
	// �̹��� ���� �߰�
	void addFile(String fullName) throws Exception;
	// list ������ �̹�������  ���
	List<String> getImageFiles(Integer moiveNo) throws Exception;
	// �̹��� ���� ����
	void deleteFiles(Integer moiveNo) throws Exception;
	
	// ���� ����/����/���� ����
	void deleteFile(String fileName) throws Exception;
	void replaceFile(String fileName, Integer moiveNo) throws Exception;
	void updateFileCnt(Integer moiveNo) throws Exception;
	
}
