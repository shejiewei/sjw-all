package com.youfan.dao;

import com.youfan.mapper.ProductDetailMapper;
import com.youfan.mapper.ProductMapper;
import com.youfan.model.Product;
import com.youfan.model.ProductDetail;
import com.youfan.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Component
public class ProductDetailDao {
    @Autowired
    ProductDetailMapper productDetailMapper;

    public int insertProduct(ProductDetail productDetail){
        return productDetailMapper.insertProductDetail(productDetail);
    }
    public ProductDetail findproductdetailbyproductid(int proudctid){
        return productDetailMapper.findproductdetailbyproductid(proudctid);
    }


}
