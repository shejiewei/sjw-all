package com.youfan.Control;

import com.youfan.model.Product;
import com.youfan.service.ProductService;
import com.youfan.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@RestController
public class ProductControl {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/listproduct",method = RequestMethod.GET)
    public List<Product> sayHi(@RequestParam int productytpeid){
        List<Product> list = productService.listproduct(productytpeid);
        System.out.println(list);
        return list;
    }





}
