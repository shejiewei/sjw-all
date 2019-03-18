package com.youfan.control;

import com.youfan.model.Product;
import com.youfan.service.ProductService;
import com.youfan.vo.ConstomProduct;
import com.youfan.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/22 0022.
 */
@RestController
public class ProductoutControl {
    @Autowired
    ProductService productService;


    @RequestMapping(value = "/listproductout",method = RequestMethod.GET)
    public List<Product> listproductout(@RequestParam int proudcttypeid){
        ConstomProduct constomProduct = new ConstomProduct();
        constomProduct.setProducttypeid(proudcttypeid);
        ProductVo productvo = new ProductVo();
        productvo.setConstomProduct(constomProduct);
        List<Product> list = productService.queryProductbyvo(productvo);
        return list;
    }

    @RequestMapping(value = "/insertoutProduct",method = RequestMethod.POST)
    public void insertProduct(@RequestBody Product product){
        product.setCreatetime(new Date());
        product.setSellnum(0);
        product.setProudctstatus(0);
        productService.insertProduct(product);
    }

    @RequestMapping(value = "/viewoutProduct",method = RequestMethod.GET)
    public Product viewProduct(int id){
        Product product = productService.findproductbyid(id);
        return product ;
    }

    @RequestMapping(value = "/deleteoutProduct",method = RequestMethod.GET)
    public void deleteProduct(int id){
        productService.deleteProductbyid(id);
    }

    @RequestMapping(value = "/updateoutProduct",method = RequestMethod.POST)
    public void updateProduct(@RequestBody Product product){
        productService.upateProduct(product);
    }


    @RequestMapping(value = "/searchproductout",method = RequestMethod.GET)
    public List<Product> searchproductout(@RequestParam String keyword){
        List<Product> list = productService.queryProductbyids(keyword);
        return list;
    }

}
