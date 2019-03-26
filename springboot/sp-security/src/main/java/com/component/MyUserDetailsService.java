package com.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by shejiewei on 2019/3/26.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger= (Logger) LoggerFactory.getLogger(getClass());
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        logger.info("用户名:"+username);
        // TODO 根据用户名，查找到对应的密码，与权限

        String password=passwordEncoder.encode("123"); //会随机生成的盐加入密码
        logger.info("password:"+password);

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
         User user=   new User(username,password,
                 true,true,true,true,
                 AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));




        return user;
    }
}
