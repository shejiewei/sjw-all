package com.youfan.control;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.youfan.model.Product;
import com.youfan.service.ProductService;
import com.youfan.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@Controller
public class ProductControl {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/insertProduct",method = RequestMethod.POST)
    public void insertProduct(Product product){
        product.setCreatetime(new Date());
        product.setSellnum(0);
        product.setProudctstatus(0);
        productService.insertProduct(product);
    }


    @RequestMapping(value = "/toinsertProduct",method = RequestMethod.GET)
    public String toinsertProduct(Product product){

        return "addproduct";
    }

    @RequestMapping(value = "/toAuditProduct",method = RequestMethod.GET)
    public String toAuditProduct(int id,Model model){
        Product product = productService.findproductbyid(id);
        model.addAttribute("product",product);
        return "auditproduct";
    }

    @RequestMapping(value = "/auditProduct",method = RequestMethod.POST)
    public void auditProduct(Product product){
        productService.auditProduct(product.getId(),product.getAuditstate());
    }

    @RequestMapping(value = "/listProduct",method = RequestMethod.GET)
    public String listProduct(Model model){
        ProductVo productvo = new ProductVo();
        List<Product> list = productService.queryProductbyvo(productvo);
        model.addAttribute("list",list);
        return "listProduct";
    }


    @RequestMapping(value = "/viewProduct",method = RequestMethod.GET)
    public String viewProduct(int id,Model model){
        Product product = productService.findproductbyid(id);
        model.addAttribute("product",product);
        return "viewproduct";
    }


    @RequestMapping(value = "/deleteProduct",method = RequestMethod.GET)
    public String deleteProduct(int id){
        productService.deleteProductbyid(id);
        return "listProduct";
    }

    @RequestMapping(value = "/toupdateProduct",method = RequestMethod.GET)
    public String toupdateProduct(int id,Model model){
        Product product = productService.findproductbyid(id);
        model.addAttribute("product",product);
        return "updateproduct";
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    public void updateProduct(Product product){
        productService.upateProduct(product);
    }

    @RequestMapping(value = "/upateProductbyproductstatus",method = RequestMethod.GET)
    public void upateProductbyproductstatus(int id,int productstatus){
        productService.upateProductbyproductstatus(id,productstatus);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String viewProduct(@RequestParam String name){
        return "hi "+name+",i am from port:";
    }



}
