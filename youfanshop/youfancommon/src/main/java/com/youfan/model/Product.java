package com.youfan.model;

import java.util.Date;

/**
 * Created by Administrator on 2018/6/18 0018.
 * 商品信息
 */
public class Product {
  private int id;//主键
  private int producttypeid;//商品类别
  private String producttitle;//商品标题
    private int productprice ;//商品价格
    private int mechartid;//商家id
    private Date createtime;//创建时间
    private Date audittime;//审核时间
    private int  auditstate;//审核转态,0,未审核 1 审核通过 2 审核不通过
    private int      stocknum;//库存
    private int  sellnum;//销售数量
    private String   productpicurl;//商品图片地址

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", producttypeid=" + producttypeid +
                ", producttitle='" + producttitle + '\'' +
                ", productprice=" + productprice +
                ", mechartid=" + mechartid +
                ", createtime=" + createtime +
                ", audittime=" + audittime +
                ", auditstate=" + auditstate +
                ", stocknum=" + stocknum +
                ", sellnum=" + sellnum +
                ", productpicurl='" + productpicurl + '\'' +
                ", proudctstatus=" + proudctstatus +
                '}';
    }

    private int proudctstatus;//产品状态，0代表上架，1代表下架

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducttypeid() {
        return producttypeid;
    }

    public void setProducttypeid(int producttypeid) {
        this.producttypeid = producttypeid;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public int getProductprice() {
        return productprice;
    }

    public void setProductprice(int productprice) {
        this.productprice = productprice;
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

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public int getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(int auditstate) {
        this.auditstate = auditstate;
    }

    public int getStocknum() {
        return stocknum;
    }

    public void setStocknum(int stocknum) {
        this.stocknum = stocknum;
    }

    public int getSellnum() {
        return sellnum;
    }

    public void setSellnum(int sellnum) {
        this.sellnum = sellnum;
    }

    public String getProductpicurl() {
        return productpicurl;
    }

    public void setProductpicurl(String productpicurl) {
        this.productpicurl = productpicurl;
    }

    public int getProudctstatus() {
        return proudctstatus;
    }

    public void setProudctstatus(int proudctstatus) {
        this.proudctstatus = proudctstatus;
    }
}
