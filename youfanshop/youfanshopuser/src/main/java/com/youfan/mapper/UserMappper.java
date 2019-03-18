package com.youfan.mapper;

import com.youfan.model.User;
import com.youfan.vo.UserVo;

import java.util.List;

/**
 * Created by youfan on 2018/6/6 0006.
 */
public interface UserMappper {
    public void inseruserInfo(User user);
    public void updateUser(User user);
    public User findByUserid(int id);
    public List<User> queryuserbyvo(UserVo userVo);
    public void deleteuserbyid(int id);
    public User findByUsername(String name);
}
