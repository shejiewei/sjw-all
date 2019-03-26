package com.configure;

import com.authentication.AuthenticationFailureHandler;
import com.authentication.AuthenticationSuccessHandler;
import com.core.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by shejiewei on 2019/3/26.
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private AuthenticationSuccessHandler myauthenticationSuccessHandler;

  @Autowired
  private AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Override
    protected  void configure(HttpSecurity http) throws  Exception{
        http.formLogin()             //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/authentication/require") // 设置登录页面
                .loginProcessingUrl("/authentication/form")  // 自定义的登录接口,转到security官方
                .successHandler(myauthenticationSuccessHandler)        //自定义登录成功后的位置
                .failureHandler(myAuthenticationFailureHandler)        //自定义失败后处理的方法
                .and()
                .authorizeRequests()// 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()  // 设置所有人都可以访问登录页面
                .anyRequest()// 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable(); // 关闭csrf防护,跨站请求的防护


    }

}
