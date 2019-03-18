package com.youfan.service;

import com.youfan.dao.ProductDao;
import com.youfan.dao.ProductDetailDao;
import com.youfan.model.Product;
import com.youfan.model.ProductDetail;
import com.youfan.utils.SolrUtil;
import com.youfan.vo.ProductVo;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@Service
public class ProductDetailService {
@Autowired
ProductDetailDao productDetailDao;
    @Autowired
    private SolrClient client;

    public void insertProduct(ProductDetail productDetail){
        try {
            int id =  productDetailDao.insertProduct(productDetail);
            Map<String, Object> mapValue = new HashMap<String,Object>();
            mapValue.put("id",productDetail.getProudctid());
            mapValue.put("productdescription",productDetail.getProductdescription());
            SolrUtil.addIndex2(client,mapValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductDetail findproductdetailbyproductid(int proudctid){
        return productDetailDao.findproductdetailbyproductid(proudctid);
    }
}
