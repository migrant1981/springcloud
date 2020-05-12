package com.example.companyservice.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.companyservice.controller.Response;

public interface IUserApi {
    @PostMapping("/user/managerUserLogin")
    Response managerUserLogin(@RequestParam("username") String username, @RequestParam("password") String password);
 
    @PostMapping("/user/queryManagerUserByEmail")
    Response queryManagerUserByEmail(@RequestParam("username") String username);
}
