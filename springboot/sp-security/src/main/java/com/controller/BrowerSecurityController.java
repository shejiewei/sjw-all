package com.controller;

import com.core.SecurityProperties;
import com.dto.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shejiewei on 2019/3/26.
 */
@RestController
public class BrowerSecurityController {

    private Logger logger= (Logger) LoggerFactory.getLogger(getClass());

     private RequestCache requestCache=  new HttpSessionRequestCache();
     private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

   @Autowired
   private SecurityProperties securityProperties;

     /*
    当需要身份认证时候跳转到这里


      */

    @RequestMapping("/authentication/require")
    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)  //返回未授权的信息

     public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
         SavedRequest savedRequest=requestCache.getRequest(request,response);//得到引发跳转的请求

           if(savedRequest!=null)
           {
               String target=savedRequest.getRedirectUrl();
               logger.info("引发跳转的请求是:"+target);
              if (StringUtils.endsWithIgnoreCase(target,".html")){
                  redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
              }
           }



           return new SimpleResponse("访问的服务需要登录页面,请引导用户到登录页");

     }



}
