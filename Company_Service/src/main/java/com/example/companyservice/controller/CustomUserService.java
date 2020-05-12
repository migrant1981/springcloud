package com.example.companyservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.companyservice.config.SecurityRole;
import com.example.companyservice.config.SecurityUser;
import com.example.companyservice.service.IUserServiceFegin;

@Component("customUserService")
public class CustomUserService implements UserDetailsService {
 
    @Autowired
    private IUserServiceFegin userServiceFegin;
 
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Response response = userServiceFegin.queryManagerUserByEmail(s);
        if (response == null) {
            System.out.println("查询空");
            return null;
        }
        if (response.getCode() == ResponseCode.SUCCESS.getCode()) {
            try {
                //转化
                String s1 = JSON.toJSONString(response);
                UserLoginEntity loginEntity = JSON.parseObject(s1, UserLoginEntity.class);
                //用户信息处理
                SecurityUser securityUser = new SecurityUser();
                securityUser.setEmail(loginEntity.getData().getManagerUser().getEmail());
                securityUser.setPassword(String.valueOf(loginEntity.getData().getManagerUser().getPassword()));
                securityUser.setSign(loginEntity.getData().getManagerUser().isSign());
                //角色处理
                List<SecurityRole> roleList = new ArrayList<>();
                List<UserLoginEntity.DataBean.RoleListBean> roleListBeans = loginEntity.getData().getRoleList();
                for (UserLoginEntity.DataBean.RoleListBean roleListBean : roleListBeans) {
                    SecurityRole securityRole = new SecurityRole();
                    securityRole.setName(roleListBean.getName());
                    securityRole.setCodeName(roleListBean.getCodeName());
                    roleList.add(securityRole);
                }
                securityUser.setRoleList(roleList);
                return securityUser;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            System.out.println(response.getCode() + ":" + response.getMsg());
            return null;
        }
    }

}
