package com.youfan.Control;

import com.youfan.model.Product;
import com.youfan.service.ProductService;
import com.youfan.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by shejiewei on 2019/3/16.
 */
@RestController
public class ProductController {
    @Autowired
    SchedualServiceHi  schedualServiceHi;

    @Autowired
    ProductService productService;




   @RequestMapping(value="/hi",method = RequestMethod.GET)
    @ResponseBody
    public String sayHi(@RequestParam String name) {

        String test = schedualServiceHi.sayHiFromClientOne(name);
        return test;

    }

    @RequestMapping(value="/listproduct",method = RequestMethod.GET)
    @ResponseBody
    public List<Product> listproduct(int typeid) {

      return productService.listproduct(typeid);

    }




}
