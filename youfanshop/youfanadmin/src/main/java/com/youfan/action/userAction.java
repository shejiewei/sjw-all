package com.youfan.action;

import com.youfan.model.User;
import com.youfan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2018/7/29 0029.
 */
@Controller
public class userAction {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listuser",method = RequestMethod.GET)
    public String listuser(Model model){
        List<User> listuser = userService.listoutUser();
        model.addAttribute("userlist",listuser);
        return "listuser";
    }
}
