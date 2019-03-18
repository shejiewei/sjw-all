package com.youfan.model;

/**
 * Created by youfan on 2018/6/8 0008.
 * 产品名称
 */
public class ProductType {
    private int id;
    private String producttypename;
    private String producttypedescription;
    private String typegrade;//等级比如1,2,3 1是最大的分类
    private int parentid;//如果是第一类别，父节点就为-1

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducttypename() {
        return producttypename;
    }

    public void setProducttypename(String producttypename) {
        this.producttypename = producttypename;
    }

    public String getProducttypedescription() {
        return producttypedescription;
    }

    public void setProducttypedescription(String producttypedescription) {
        this.producttypedescription = producttypedescription;
    }

    public String getTypegrade() {
        return typegrade;
    }

    public void setTypegrade(String typegrade) {
        this.typegrade = typegrade;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }
}
