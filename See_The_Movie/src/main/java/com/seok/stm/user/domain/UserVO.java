package com.seok.stm.user.domain;

import java.util.Date;

public class UserVO {

	private String userId;
	private String userPass;
	private String userName;
	private String userEmail;
	private Date userJoinTime;
	private Date userLoginTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getUserJoinTime() {
		return userJoinTime;
	}
	public void setUserJoinTime(Date userJoinTime) {
		this.userJoinTime = userJoinTime;
	}
	public Date getUserLoginTime() {
		return userLoginTime;
	}
	public void setUserLoginTime(Date userLoginTime) {
		this.userLoginTime = userLoginTime;
	}
	
	
}
