package com.seok.stm.user.persistence;

import java.util.Date;
import java.util.List;

import com.seok.stm.user.domain.LoginDTO;
import com.seok.stm.user.domain.UserVO;


public interface UserDAO {
	
	void register(UserVO userVO) throws Exception; //ȸ������ ó��
	UserVO userView(String userId) throws Exception; //ȸ������ ��ȸ
	void userUpdate(UserVO userVO) throws Exception; //ȸ������ ����
	UserVO login(LoginDTO loginDTO) throws Exception; //�α��� ó��
	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception; // �α��� ���� ó��
	UserVO checkUserWithSessionKey(String value) throws Exception; //����Ű ����
	List<UserVO> userList() throws Exception;
//	List<UserVO> userList(SearchUser searchUser) throws Exception;
	void loginTimeUpdate(UserVO userVO) throws Exception;
}
