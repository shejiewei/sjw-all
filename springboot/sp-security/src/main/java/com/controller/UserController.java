package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shejiewei on 2019/3/26.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String getUsers(){
        return "hello user";
    }


}
