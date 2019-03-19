package com.youfan.service;

import com.youfan.model.Product;
import com.youfan.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2018/7/29 0029.
 */
@FeignClient(value = "youfanshopuser")
public interface UserService {

    @RequestMapping(value = "/listoutUser",method = RequestMethod.GET)
    public List<User> listoutUser();

}
