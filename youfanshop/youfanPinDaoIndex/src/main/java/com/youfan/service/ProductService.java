package com.youfan.service;
import com.youfan.model.Product;
import com.youfan.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@FeignClient(value = "youfanproduct")
public interface ProductService {
    @RequestMapping(value = "/listproductout",method = RequestMethod.GET)
    List<Product> listproduct(@RequestParam(value = "proudcttypeid") int producttyeid);

    @RequestMapping(value = "/searchproductout",method = RequestMethod.GET)
    public List<Product> searchproductout(@RequestParam(value = "keyword") String keyword);

    @RequestMapping(value = "/findproductdetailbyproductid",method = RequestMethod.POST)
    public ProductDetail findproductdetailbyproductid(@RequestParam(value = "proudctid") int proudctid);

    @RequestMapping(value = "/viewoutProduct",method = RequestMethod.GET)
    public Product viewProduct(@RequestParam(value = "id") int id);
}
