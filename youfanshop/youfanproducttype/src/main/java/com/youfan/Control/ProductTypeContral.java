package com.youfan.Control;

import com.youfan.model.ProductType;
import com.youfan.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by youfan on 2018/6/8 0008.
 */
@Controller
public class ProductTypeContral {

    @Autowired
    ProductTypeService productTypeService;

    @RequestMapping(value = "/insertProducttype",method = RequestMethod.POST)
    public void insertProducttype(ProductType productType){
        productTypeService.insertProducttype(productType);
    }

    @RequestMapping(value = "/findProductytpeByid",method = RequestMethod.GET)
    public String findProductytpeByid(int id, Model model){
        ProductType productType = productTypeService.findProductytpeByid(id);
        if(productType == null){
            productType = new ProductType();
            productType.setId(-1);
        }
        model.addAttribute("productType",productType);
        return "producttypeadd";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String viewProduct(@RequestParam String name){

        return "hi "+name+",i am from port:";
    }
    @RequestMapping(value="/viewtest",method=RequestMethod.GET)

    public String viewTest(@RequestParam String name){

        return "hi"+name+",i am from youfanproductype";
    }

}
