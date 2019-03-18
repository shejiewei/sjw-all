package com.youfan.control;

import com.youfan.model.User;
import com.youfan.service.UserService;
import com.youfan.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by youfan on 2018/6/6 0006.
 */
@Controller
public class UserControl {
    @Autowired
    UserService userServive;




    @RequestMapping(value = "/touserregister",method = RequestMethod.GET)
    public String touserregister() {
        return "userregister";
    }

    @RequestMapping(value = "/userregister",method = RequestMethod.POST)
    public void userregister(User user) {
        userServive.inseruserInfo(user);
       return;
    }

    @RequestMapping(value = "/toupdateUser",method = RequestMethod.GET)
    public String toupdateUser(int id,Model model){
        User user =  userServive.findByUserid(id);
        model.addAttribute("user",user);
        return "userupdate";
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public void updateUser(User user){
        userServive.updateUser(user);
    }

    @RequestMapping(value = "/findByUserid",method = RequestMethod.GET)
    public String findByUserid(int id,Model model){
        User user =  userServive.findByUserid(id);
        model.addAttribute("user",user);
        return "userview";
    }

    @RequestMapping(value = "/queryuserbyvo",method = RequestMethod.GET)
    public String queryuserbyvo(Model model){
        UserVo userVo = new UserVo();
        List<User> listuser = userServive.queryuserbyvo(userVo);
        model.addAttribute("listuser",listuser);
        return "userlist";
    }
    @RequestMapping(value = "/deleteuserbyid",method = RequestMethod.GET)
    public void deleteuserbyid(int id){
        userServive.deleteuserbyid(id);
    }



}
