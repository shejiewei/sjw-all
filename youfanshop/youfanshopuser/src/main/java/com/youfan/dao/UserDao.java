package com.youfan.dao;

import com.youfan.mapper.UserMappper;
import com.youfan.model.User;
import com.youfan.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by youfan on 2018/6/6 0006.
 */
@Component
public class UserDao {

    @Autowired
    UserMappper userMappper;

    public void inseruserInfo(User user){
        userMappper.inseruserInfo(user);
    }

    public void updateUser(User user){
        userMappper.updateUser(user);
    }
    public User findByUserid(int id){
        return userMappper.findByUserid(id);
    }

    public List<User> queryuserbyvo(UserVo userVo){
        return userMappper.queryuserbyvo(userVo);
    }

    public void deleteuserbyid(int id){
        userMappper.deleteuserbyid(id);
    }

    public User findByUsername(String name){
        return userMappper.findByUsername(name);
    }
}
