package com.youfan.model;

/**
 * Created by youfan on 2018/6/8 0008.
 */
public class Mechant {
    private int id;
    private String merchantname;
    private String merchantshopname;
    private String merchantaccount;
    private String mechantpassword;
    private String mechantscope;
    private int auditstatus;//1提交成功，2审核通过，3审核不通过
    private int soldout;//1,正常，2,下架

    public int getSoldout() {
        return soldout;
    }

    public void setSoldout(int soldout) {
        this.soldout = soldout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }

    public String getMerchantshopname() {
        return merchantshopname;
    }

    public void setMerchantshopname(String merchantshopname) {
        this.merchantshopname = merchantshopname;
    }

    public String getMerchantaccount() {
        return merchantaccount;
    }

    public void setMerchantaccount(String merchantaccount) {
        this.merchantaccount = merchantaccount;
    }

    public String getMechantpassword() {
        return mechantpassword;
    }

    public void setMechantpassword(String mechantpassword) {
        this.mechantpassword = mechantpassword;
    }

    public String getMechantscope() {
        return mechantscope;
    }

    public void setMechantscope(String mechantscope) {
        this.mechantscope = mechantscope;
    }

    public int getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(int auditstatus) {
        this.auditstatus = auditstatus;
    }
}
