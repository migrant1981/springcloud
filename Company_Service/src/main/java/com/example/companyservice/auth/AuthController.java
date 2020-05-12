package com.example.companyservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.companyservice.controller.CustomUserService;
import com.example.companyservice.controller.Response;
import com.example.companyservice.controller.ResponseCode;
import com.example.companyservice.controller.UserLoginEntity;
import com.example.companyservice.jwt.JwtTokenUtil;
import com.example.companyservice.service.IUserServiceFegin;

@RestController
public class AuthController {
 
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
 
    @Autowired
    private CustomUserService customUserService;
 
    @Autowired
    private IUserServiceFegin userServiceFegin;
 
    @RequestMapping("/login")
    public Response login(String username, String password) {
        Response response = userServiceFegin.managerUserLogin(username, password);
        if (response == null) {
            return new Response().ResponseErrorData("网关获取对象数据失败-1");
        }
        if (response.getCode() == ResponseCode.SUCCESS.getCode()) {
            String s1 = JSON.toJSONString(response);
            UserLoginEntity loginEntity = JSON.parseObject(s1, UserLoginEntity.class);
            if (loginEntity.getData() == null) {
                return new Response().ResponseErrorData("网关获取对象数据失败-2");
            }
            if (loginEntity.getData().getManagerUser() == null) {
                return new Response().ResponseErrorData("网关获取对象数据失败-3");
            }
            if (StringUtils.isEmpty(loginEntity.getData().getManagerUser().getEmail())) {
                return new Response().ResponseErrorData("网关获取对象数据失败-4");
            }
            UserDetails userDetails = customUserService.loadUserByUsername(loginEntity.getData().getManagerUser().getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);//获取Token
            loginEntity.getData().setToken(token);//设置Token
            return new Response().ResponseSucessData(loginEntity.getData());
        } else {
            return response;
        }
    }
}
