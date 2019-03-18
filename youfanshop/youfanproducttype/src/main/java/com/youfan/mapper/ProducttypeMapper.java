package com.youfan.mapper;

import com.youfan.model.ProductType;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
public interface ProducttypeMapper {
    public void insertProducttype(ProductType productType);
    public ProductType findProductytpeByid(int id);
    public List<ProductType> listallProductytpe();
    public void updateproductTypebyid(ProductType productType);
}
