package com.youfan.service;

import com.youfan.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/7/29 0029.
 */
@FeignClient(value = "youfanshopuser")
public interface UserService {

    @RequestMapping(value = "/useroutregister",method = RequestMethod.POST)
    public void userregister(@RequestBody User user) ;

    @RequestMapping(value = "/findByUsername",method = RequestMethod.GET)
    public User findByUsername(@RequestParam(value = "name") String name);

}
