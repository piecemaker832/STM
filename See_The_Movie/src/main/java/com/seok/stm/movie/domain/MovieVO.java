package com.seok.stm.movie.domain;

import java.util.Date;

public class MovieVO {
	
	private Integer movieNo; // �Խñ� ��ȣ
	private String movieTitle; // ��ȭ����
	private String movieSummary; // ��ȭ�Ұ�
	private Date movieDate;  //�ۼ��ð�
	private String movieWriter; // �ۼ���
	private Integer movieRating; // ��ȭ ����
	private Integer replyCnt; // ��۰���
	private Integer movieHit; // �� ��ȸ��
	private String[] files; //÷������ ���
	private int fileCnt; // ���ϰ���
	private String mainImage; // �����̹���
	
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
