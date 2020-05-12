package com.example.startsstock.impl;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.startsstock.Service.User;
import com.example.startsstock.entity.UserVO;
import com.example.startsstock.repository.UserVORepository;

@Service
public class UserImpl implements User {
	
	public static final String COOKIE_NAME_TOKEN = "token";
	public static final int TOKEN_EXPIRE = 3600 * 24 * 2;
	
	@Autowired
	private UserVORepository userVORepository;
	
	@Override
	public List<UserVO> getUserDetail(String username) {
		List<UserVO> list= this.userVORepository.getUserDetail(username);
		return list;
	}
	
	@Override
	public UserVO findUserByusername(String username) {		
		UserVO userVO = this.userVORepository.findUserByusername(username);
		return userVO;
	}
	
	@Override
	public UserVO getUserDetailById( Long id) {		
		UserVO userVO = this.userVORepository.getUserDetailById(id);
		return userVO;
	}
	
	@Override
	public String checkUser(String username, String password) {
		UserVO userVO = this.findUserByusername(username);
		if (userVO == null) {
			return "user is not exists";
		}
		String dbPass = userVO.getPassword();
        if (!password.equals(dbPass)) {
            return "password is error";
        }
      //生成cookie
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;        
	}	
	
	@Override
	public void save(UserVO c) {
		this.userVORepository.save(c);
	}
	
	@Override
	public Integer updatepwd(String username, String password) {
		return this.userVORepository.updatePWDByName(username, password);
	}
	
}
