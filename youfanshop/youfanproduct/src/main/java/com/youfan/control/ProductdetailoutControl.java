package com.youfan.control;

import com.youfan.model.Product;
import com.youfan.model.ProductDetail;
import com.youfan.service.ProductDetailService;
import com.youfan.service.ProductService;
import com.youfan.vo.ProductVo;
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
public class ProductdetailoutControl {
    @Autowired
    ProductDetailService productDetailService;

    @RequestMapping(value = "/insertProductoutDetail",method = RequestMethod.POST)
    public void insertProduct(@RequestBody  ProductDetail productDetail){
        productDetailService.insertProduct(productDetail);
    }

    @RequestMapping(value = "/findproductdetailbyproductid",method = RequestMethod.POST)
    public ProductDetail findproductdetailbyproductid(@RequestParam int proudctid){
       return productDetailService.findproductdetailbyproductid(proudctid);
    }

}
