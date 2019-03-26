package com.sjw;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shejiewei on 2019/3/20.
 */


@RestController
public class DemoApplication {

    @RequestMapping("/hello")
    public void sayhello(){
        System.out.println("hello");
    }

}
