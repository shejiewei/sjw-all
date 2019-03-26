package com.component;

import org.springframework.security.core.userdetails.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by shejiewei on 2019/3/26.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger= (Logger) LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        logger.info("用户名:"+username);
        // TODO 根据用户名，查找到对应的密码，与权限

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
         User user=   new User(username,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));




        return user;
    }
}
