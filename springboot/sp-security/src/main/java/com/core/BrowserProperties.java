package com.core;

/**
 * Created by shejiewei on 2019/3/26.
 */
public class BrowserProperties {

     private String loginPage="/imooc-signIn.html";  //设置默认值

    private LoginType loginType=LoginType.JSON;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
