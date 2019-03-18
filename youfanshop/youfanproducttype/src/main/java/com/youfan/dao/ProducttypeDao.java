package com.youfan.dao;

import com.youfan.mapper.ProducttypeMapper;
import com.youfan.model.ProductType;
import com.youfan.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Component
public class ProducttypeDao {
    @Autowired
    ProducttypeMapper producttypeMapper;

    public void insertProducttype(ProductType productType){
        producttypeMapper.insertProducttype(productType);
    }
    public ProductType findProductytpeByid(int id){
        return producttypeMapper.findProductytpeByid(id);
    }
    public List<ProductType> listallProductytpe(){

        return producttypeMapper.listallProductytpe();
    }
    public void updateproductTypebyid(ProductType productType){
        producttypeMapper.updateproductTypebyid(productType);
    }
}
