package com.example.companyservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import com.example.companyservice.controller.UserFallBack;

@Component("userServiceFegin")
@FeignClient(value = "tm-fenghua-user", fallback = UserFallBack.class)
public interface IUserServiceFegin extends IUserApi {

}
