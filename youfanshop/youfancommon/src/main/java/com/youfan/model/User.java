package com.youfan.model;

/**
 * Created by youfan on 2018/6/6 0006.
 */
public class User {
    private int id;//主键
    private String name;//姓名
    private int age ;//年龄
    private String passwordencrypt;//密码
    private String address;//地址
    private String telphone;//手机号
    private String qq;//qq
    private String weixin;//微信
    private String email;//邮箱
    private String sex;//性别
    private String account;//用户名

    private String birthday;//生日时间
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPasswordencrypt() {
        return passwordencrypt;
    }

    public String getAddress() {
        return address;
    }

    public String getTelphone() {
        return telphone;
    }

    public String getQq() {
        return qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPasswordencrypt(String passwordencrypt) {
        this.passwordencrypt = passwordencrypt;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


}
