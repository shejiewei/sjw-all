package com.youfan.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by shejiewei on 2019/3/16.
 */
@FeignClient(value = "youdaoproducttype")
public interface  sayHi{
    @RequestMapping(value = "/viewtest",method = RequestMethod.GET)
    String sayHi(@RequestParam(value = "name") String name);
}
