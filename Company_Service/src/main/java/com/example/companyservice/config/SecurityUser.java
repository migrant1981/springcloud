package com.example.companyservice.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUser implements Serializable, UserDetails {
 
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
 
    private static final long serialVersionUID = 1L;
 
    private String email;
 
    private String password;
 
    private Boolean sign;
 
 
    private List<SecurityRole> roleList;
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public Boolean getSign() {
        return sign;
    }
 
    public void setSign(Boolean sign) {
        this.sign = sign;
    }
 
    public List<SecurityRole> getRoleList() {
        return roleList;
    }
 
    public void setRoleList(List<SecurityRole> roleList) {
        this.roleList = roleList;
    }
 
    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将用户角色作为权限
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<SecurityRole> roles = this.getRoleList();
        for (SecurityRole role : roles) {
            System.out.println(role.getCodeName());
            auths.add(new SimpleGrantedAuthority(role.getCodeName()));
        }
        return auths;
    }
 
    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public String getUsername() {
        return email;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }

}
