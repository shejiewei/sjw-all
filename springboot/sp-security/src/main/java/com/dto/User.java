package com.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by shejiewei on 2019/3/26.
 */
public class User {

    private  String username;
    @NotBlank
    private String password;


    public User(String username, String password, List<GrantedAuthority> admin){
        this.username=username;
        this.password=password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
