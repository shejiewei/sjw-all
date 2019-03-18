package com.youfan.service;

import com.youfan.dao.UserDao;
import com.youfan.model.User;
import com.youfan.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by youfan on 2018/6/6 0006.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public void inseruserInfo(User user){
        userDao.inseruserInfo(user);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }
    public User findByUserid(int id){
        return userDao.findByUserid(id);
    }

    public List<User> queryuserbyvo(UserVo userVo){
        return userDao.queryuserbyvo(userVo);
    }

    public void deleteuserbyid(int id){
        userDao.deleteuserbyid(id);
    }

    public User findByUsername(String name){
        return userDao.findByUsername(name);
    }
}
