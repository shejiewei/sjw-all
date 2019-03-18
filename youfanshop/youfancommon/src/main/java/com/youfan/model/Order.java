package com.youfan.model;

import java.util.Date;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
public class Order {
   private int id;//主键
    private double payamount;//支付金额
    private int userid;//用户id
    private Date createtime;//创建时间
    private Date paytime;//支付时间
    private int  paystatus;//支付状态 支付状态,1未支付 2已支付 3已退款
    private String consigneeadress;//收货人地址
    private String consigneephone;//收货人电话
    private String consigneename;//收货人姓名
    private String tradenumber;//交易流水号
    private int paytype;//支付类型
    private int orderstatus;//订单状态 0 正常，1 取消

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPayamount() {
        return payamount;
    }

    public void setPayamount(double payamount) {
        this.payamount = payamount;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public int getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(int paystatus) {
        this.paystatus = paystatus;
    }

    public String getConsigneeadress() {
        return consigneeadress;
    }

    public void setConsigneeadress(String consigneeadress) {
        this.consigneeadress = consigneeadress;
    }

    public String getConsigneephone() {
        return consigneephone;
    }

    public void setConsigneephone(String consigneephone) {
        this.consigneephone = consigneephone;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }

    public String getTradenumber() {
        return tradenumber;
    }

    public void setTradenumber(String tradenumber) {
        this.tradenumber = tradenumber;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public int getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(int orderstatus) {
        this.orderstatus = orderstatus;
    }
}
