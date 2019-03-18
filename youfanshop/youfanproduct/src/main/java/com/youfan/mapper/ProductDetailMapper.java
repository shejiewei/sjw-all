package com.youfan.mapper;

import com.youfan.model.Product;
import com.youfan.model.ProductDetail;
import com.youfan.vo.ProductVo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
public interface ProductDetailMapper {
    public int insertProductDetail(ProductDetail productDetail);

    public ProductDetail findproductdetailbyproductid(int proudctid);
}
