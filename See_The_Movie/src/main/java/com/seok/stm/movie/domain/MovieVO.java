package com.seok.stm.movie.domain;

import java.util.Date;

public class MovieVO {
	
	private Integer movieNo; // 게시글 번호
	private String movieTitle; // 영화제목
	private String movieSummary; // 영화소개
	private Date movieDate;  //작성시간
	private String movieWriter; // 작성자
	private Integer movieRating; // 영화 평점
	private Integer replyCnt; // 댓글갯수
	private Integer movieHit; // 글 조회수
	private String[] files; //첨부파일 목록
	private int fileCnt; // 파일갯수
	private String mainImage; // 메인이미지
	
	public Integer getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(Integer movieNo) {
		this.movieNo = movieNo;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieSummary() {
		return movieSummary;
	}
	public void setMovieSummary(String movieSummary) {
		this.movieSummary = movieSummary;
	}
	public Date getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(Date movieDate) {
		this.movieDate = movieDate;
	}
	public String getMovieWriter() {
		return movieWriter;
	}
	public void setMovieWriter(String movieWriter) {
		this.movieWriter = movieWriter;
	}
	public Integer getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(Integer movieRating) {
		this.movieRating = movieRating;
	}
	public Integer getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}
	public Integer getMovieHit() {
		return movieHit;
	}
	public void setMovieHit(Integer movieHit) {
		this.movieHit = movieHit;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public int getFileCnt() {
		return fileCnt;
	}
	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return ("movieNo : "+movieNo+"movieTitle : " + movieTitle + "movieSummary : " + movieSummary +
//				"movieDate : " + movieDate + "movieWriter : " + movieWriter + "movieRating : " + movieRating +
//				"mainImage : " + mainImage);
//	}
}
