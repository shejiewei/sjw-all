package com.youfan.control;

import com.youfan.model.User;
import com.youfan.service.UserService;
import com.youfan.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by youfan on 2018/6/6 0006.
 */
@RestController
public class UserOutControl {
    @Autowired
    UserService userServive;

    @RequestMapping(value = "/listoutUser",method = RequestMethod.GET)
    public List<User> listoutUser() {
        UserVo userVo = new UserVo();
        List<User> listuser = userServive.queryuserbyvo(userVo);
        return listuser;
    }

    @RequestMapping(value = "/useroutregister",method = RequestMethod.POST)
    public void userregister(@RequestBody  User user) {
        userServive.inseruserInfo(user);
        return;
    }

    @RequestMapping(value = "/findByUsername",method = RequestMethod.GET)
    public User findByUsername(@RequestParam String  name, Model model){
        User user =  userServive.findByUsername(name);
        return user;
    }
}
