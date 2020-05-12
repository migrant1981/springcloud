package com.example.startsstock.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.startsstock.Service.User;
import com.example.startsstock.entity.UserVO;
import com.example.startstock.common.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private User User;
	private int code;
    private String msg;
	
	@RequestMapping("/current")
    @ResponseBody
    public Result<User> current(User user) {
        return Result.success(user);
    }
	
	@CrossOrigin
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestBody UserVO user) {
		System.out.print("username : "+ user.getUsername() + " pwd: " + user.getPassword());
		String token = this.User.checkUser(user.getUsername(), user.getPassword());
		System.out.print(token);
		UserVO userlist = this.User.findUserByusername(user.getUsername());
		
		Map<String, Object> map = new HashMap<>(1);
		map.put("userinfo", userlist);
        map.put("token", token);

		return map;
    }
	
	@CrossOrigin
    @RequestMapping(value="/signup", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@RequestBody UserVO user) {
	  Map<String, Object> map = new HashMap<>(1);
	  UserVO isNewUser = this.User.findUserByusername(user.getUsername());
      if (isNewUser == null) {
    	  user.setId(user.getId());
    	  user.setUsername(user.getUsername());
    	  user.setPassword(user.getPassword());
    	  user.setEmail(user.getEmail());
    	  user.setMobile(user.getMobile());
    	  user.setUsertype(user.getUsertype());
    	  user.setConfirmed(user.getConfirmed());
    	  User.save(user);
    	  UserVO userVO = this.User.findUserByusername(user.getUsername());
    	  map.put("userinfo", userVO);
      }
      return map;
    }
	
	@CrossOrigin
	@RequestMapping(value="/changepwd", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changepwd(@RequestBody UserVO user) {
		Map<String, Object> map = new HashMap<>(2);
		UserVO userVO = this.User.findUserByusername(user.getUsername());
		if (userVO == null) {
			this.msg = "user is not exists";
			this.code = 402;
		}
		this.code = 200;
		this.User.updatepwd(user.getUsername(), user.getPassword());
		map.put("userinfo", userVO);
		map.put("msg", this.msg);
		map.put("code", this.code);
		return map;
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserDetails(@RequestBody UserVO user) {
		Map<String, Object> map = new HashMap<>(1);
		UserVO userVO = this.User.getUserDetailById(user.getId());
		map.put("userinfo", userVO);
		return map;
	}	
}
