package com.youfan.Control;

import com.youfan.model.Product;
import com.youfan.model.ProductType;
import com.youfan.service.ProductService;
import com.youfan.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@Controller
public class ProducttypeControl {

    @Autowired
    ProductTypeService productTypeService;

    @RequestMapping(value = "/listproducttype",method = RequestMethod.GET)
    public String listproducttype(Model model){
        List<ProductType> list = productTypeService.listproductType();
        model.addAttribute("productypelist",list);
        System.out.println(list);
        return "list";
    }



}
