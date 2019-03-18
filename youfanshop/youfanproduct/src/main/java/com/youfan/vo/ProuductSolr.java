package com.youfan.vo;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Administrator on 2018/8/5 0005.
 */
public class ProuductSolr {
    @Field
    private String id;

    @Field
    private String producttitle;

    @Field
    private String productdescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }
}
