package com.youfan.Control;

import com.youfan.model.ProductType;
import com.youfan.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@RestController
public class ProductTypeoutContral {

    @Autowired
    ProductTypeService productTypeService;



    @RequestMapping(value = "/listproducttype",method = RequestMethod.GET)
    public List<ProductType> listproducttype(){
        List<ProductType> list = productTypeService.listallProductytpe();
        return list;
    }

    @RequestMapping(value = "/insertoutProducttype",method = RequestMethod.POST)
    public void insertProducttype(@RequestBody ProductType productType){
        productTypeService.insertProducttype(productType);
    }

    @RequestMapping(value = "/findoutProductytpeByid",method = RequestMethod.GET)
    public ProductType findProductytpeByid(@RequestParam int id){
        ProductType productType = productTypeService.findProductytpeByid(id);
        if(productType == null){
            productType = new ProductType();
            productType.setId(-1);
        }
        return productType;
    }


    @RequestMapping(value = "/updateoutProductytpeByid",method = RequestMethod.POST)
    public void updateProductytpeByid(@RequestBody ProductType productType){
        productTypeService.updateproductTypebyid(productType);
    }
}
