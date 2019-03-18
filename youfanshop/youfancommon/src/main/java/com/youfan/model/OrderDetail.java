package com.youfan.model;

import java.util.Date;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
public class OrderDetail {
   private int id;//主键
    private int orderid;//订单id
    private int productid;//商品id
    private int mechartid;//商家id
    private Date createtime;//创建时间
    private int  tradenum;//交易数量

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getMechartid() {
        return mechartid;
    }

    public void setMechartid(int mechartid) {
        this.mechartid = mechartid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getTradenum() {
        return tradenum;
    }

    public void setTradenum(int tradenum) {
        this.tradenum = tradenum;
    }
}
