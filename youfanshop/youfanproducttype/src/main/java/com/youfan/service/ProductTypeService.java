package com.youfan.service;

import com.youfan.dao.ProducttypeDao;
import com.youfan.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Service
public class ProductTypeService {
    @Autowired
    ProducttypeDao producttypeDao;

    public void insertProducttype(ProductType productType){
        producttypeDao.insertProducttype(productType);
    }
    public ProductType findProductytpeByid(int id){
        return producttypeDao.findProductytpeByid(id);
    }
    public List<ProductType> listallProductytpe(){
        return producttypeDao.listallProductytpe();
    }
    public void updateproductTypebyid(ProductType productType){
        producttypeDao.updateproductTypebyid(productType);
    }
}
