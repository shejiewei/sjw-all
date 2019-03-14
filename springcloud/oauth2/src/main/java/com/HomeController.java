package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shejiewei on 2019/3/14.
 */
@RestController
public class HomeController {
  @RequestMapping("/home")
    public String Home(){

        return "hello home";
    }

}
