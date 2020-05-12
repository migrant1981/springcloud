package com.example.startsstock.Service;

import java.util.List;

import com.example.startsstock.entity.UserVO;

public interface User {
	void save(UserVO c);

	UserVO findUserByusername(String username);
	
	UserVO getUserDetailById(Long id);

    List<UserVO> getUserDetail(String username);
    
    String checkUser(String username, String password);
    
    Integer updatepwd(String username, String password);


}
