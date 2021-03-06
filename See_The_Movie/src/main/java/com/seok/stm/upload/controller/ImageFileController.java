package com.seok.stm.upload.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seok.stm.commons.paging.PageMaker;
import com.seok.stm.commons.paging.SearchCriteria;
import com.seok.stm.commons.util.UploadFileUtils;
import com.seok.stm.upload.service.ImageFileService;


@RestController
@RequestMapping("/movie/file")
public class ImageFileController {
	
	private final ImageFileService imageFileService;
	
	@Inject
	public ImageFileController(ImageFileService imageFileService) {
		this.imageFileService = imageFileService;
	}
	
	// 게시글 파일(작성/수정시) 업로드
	@RequestMapping(value="/upload", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> uploadFile(MultipartFile file, HttpServletRequest request){
		ResponseEntity<String> entity = null;
		try {
			String savedFilePath = UploadFileUtils.uploadFile(file, request);  
			System.out.println(savedFilePath);
			entity = new ResponseEntity<>(savedFilePath, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 게시글 첨부파일 출력
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ResponseEntity<byte[]>displayFile(String fileName, HttpServletRequest request) throws Exception{
		HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName); // Http 헤더 설정 가져오기
		String rootPath = UploadFileUtils.getRootPath(fileName, request); // 업로드 기본경로 경로
		System.out.println(fileName+rootPath);
		
		ResponseEntity<byte[]> entity = null;
		
		//파일데이터, HttpHeader 전송
		try(InputStream inputStream = new FileInputStream(rootPath + fileName)) {
			entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//게시판 파일 삭제 : 게시글 작성 페이지
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName, HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		
		try {
			UploadFileUtils.deleteFile(fileName, request);
			entity = new ResponseEntity<>("DELETED",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 게시글 첨부 파일 목록
	@RequestMapping(value="/list/{boardNo}", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getFiles(@PathVariable("movieNo") Integer movieNo){
		ResponseEntity<List<String>> entity = null;
		try {
			List<String> fileList = imageFileService.getImageFiles(movieNo);
			entity = new ResponseEntity<>(fileList,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 게시글 파일 전체 삭제
	@RequestMapping(value="/deleteAll", method=RequestMethod.POST)
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files, HttpServletRequest request){
		
		if(files == null || files.length == 0)
			return new ResponseEntity<>("DELETED",HttpStatus.OK);
		
		ResponseEntity<String> entity = null;
		
		try {
			for(String fileName : files)
				UploadFileUtils.deleteFile(fileName, request);
			entity = new ResponseEntity<>("DELETED",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 게시글 첨부파일 삭제 (게시글 수정페이지)
	@RequestMapping(value = "/delete/{movieNo}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@PathVariable("movieNo") Integer movieNo, String fileName, HttpServletRequest request){
		
		ResponseEntity<String> entity = null;
		
		try {
			//서버에 저장된 파일 삭제
			UploadFileUtils.deleteFile(fileName, request);
			//DB에 저장된 파일 삭제
			imageFileService.deleteFile(fileName,movieNo);
			entity = new ResponseEntity<>("DELETED",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
