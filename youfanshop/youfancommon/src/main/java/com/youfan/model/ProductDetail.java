package com.youfan.model;

/**
 * Created by Administrator on 2018/6/18 0018.
 * 商品详情信息
 */
public class ProductDetail {
    private int id;//主键
    private int proudctid;//商品id
    private String productplace;//商品产地
    private String  productdescription;//商品描述
    private String   productbrand;//商品品牌
    private String   productweight;//商品重量
    private String    productspecification;//商品规格说明和包装
    private String     productdetaipicurl;//商品详情图片地址

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProudctid() {
        return proudctid;
    }

    public void setProudctid(int proudctid) {
        this.proudctid = proudctid;
    }

    public String getProductplace() {
        return productplace;
    }

    public void setProductplace(String productplace) {
        this.productplace = productplace;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProductbrand() {
        return productbrand;
    }

    public void setProductbrand(String productbrand) {
        this.productbrand = productbrand;
    }

    public String getProductweight() {
        return productweight;
    }

    public void setProductweight(String productweight) {
        this.productweight = productweight;
    }

    public String getProductspecification() {
        return productspecification;
    }

    public void setProductspecification(String productspecification) {
        this.productspecification = productspecification;
    }

    public String getProductdetaipicurl() {
        return productdetaipicurl;
    }

    public void setProductdetaipicurl(String productdetaipicurl) {
        this.productdetaipicurl = productdetaipicurl;
    }
}
