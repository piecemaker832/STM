package com.seok.stm.upload.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seok.stm.upload.persistence.ImageFileDAO;



@Service
public class ImageFileServiceImpl implements ImageFileService {
	
	private final ImageFileDAO imageFilesDAO;
	
	@Inject
	public ImageFileServiceImpl(ImageFileDAO imageFilesDAO) {
		// TODO Auto-generated constructor stub
		this.imageFilesDAO = imageFilesDAO;
	}
	
	//첨부파일목록
	@Override
	public List<String> getImageFiles(Integer movieNo) throws Exception {
		// TODO Auto-generated method stub
		return imageFilesDAO.getImageFiles(movieNo);
	}
	
	
    @Transactional
    @Override
    public void deleteFile(String fileName, Integer boardNo) throws Exception {
    	imageFilesDAO.deleteFile(fileName);
    	imageFilesDAO.updateFileCnt(boardNo);
    }
}
