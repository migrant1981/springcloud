package com.example.companyservice.controller;

import org.springframework.stereotype.Component;

import com.example.companyservice.service.IUserServiceFegin;

@Component
public class UserFallBack implements IUserServiceFegin {
 
    @Override
    public Response managerUserLogin(String username, String password) {
        return new Response(ResponseCode.SERVER_DOWNGRADE, "服务降级");
    }
 
    @Override
    public Response queryManagerUserByEmail(String username) {
        return new Response(ResponseCode.SERVER_DOWNGRADE, "服务降级");
    }

}
