package com.youfan.action;

import com.youfan.model.Product;
import com.youfan.model.ProductDetail;
import com.youfan.model.User;
import com.youfan.service.ProductDetailService;
import com.youfan.service.ProductService;
import com.youfan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/29 0029.
 */
@Controller
public class ProductAction {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService productDetailService;

    @RequestMapping(value = "/listProduct",method = RequestMethod.GET)
    public String listProduct(Model model,int producttypeid){
        List<Product> list = productService.listproduct(producttypeid);
        model.addAttribute("productlist",list);
        return "listproduct";
    }


    @RequestMapping(value = "/toinsertProduct",method = RequestMethod.GET)
    public String toinsertProduct(){

        return "addproduct";
    }

    @RequestMapping(value = "/insertProduct",method = RequestMethod.POST)
    public void insertProduct(Product product){
        product.setCreatetime(new Date());
        product.setSellnum(0);
        product.setProudctstatus(0);
        productService.insertProduct(product);
    }

    @RequestMapping(value = "/viewProduct",method = RequestMethod.GET)
    public String viewProduct(int id,Model model){
        Product product = productService.viewProduct(id);
        model.addAttribute("product",product);
        return "viewproduct";
    }

    @RequestMapping(value = "/deleteProduct",method = RequestMethod.GET)
    public String deleteProduct(int id){
        productService.deleteProduct(id);
        return "listproduct";
    }

    @RequestMapping(value = "/toupdateProduct",method = RequestMethod.GET)
    public String toupdateProduct(int id,Model model){
        Product product = productService.viewProduct(id);
        model.addAttribute("product",product);
        return "updateproduct";
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    public void updateProduct(Product product){
        productService.updateProduct(product);
    }

    @RequestMapping(value = "/toaddproductdetail",method = RequestMethod.GET)
    public String toaddproductdetail(int id,Model model){
        Product product = productService.viewProduct(id);
        model.addAttribute("id",id);
        return "addproductdetail";
    }

    @RequestMapping(value = "/addproductdetail",method = RequestMethod.POST)
    public void toaddproductdetail(ProductDetail productDetail){
        productDetailService.insertProduct(productDetail);
    }

}
