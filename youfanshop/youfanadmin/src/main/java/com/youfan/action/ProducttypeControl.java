package com.youfan.action;

import com.youfan.model.ProductType;
import com.youfan.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "listtype";
    }

    @RequestMapping(value = "/listproducttypebypr",method = RequestMethod.GET)
    public String listproducttypebypr(Model model){
        List<ProductType> list = productTypeService.listproductType();
        model.addAttribute("productypelist",list);
        System.out.println(list);
        return "listtypebypr";
    }

    @RequestMapping(value = "/toinsertProducttype",method = RequestMethod.GET)
    public String toinsertProducttype(){
        return "producttypeadd";
    }

    @RequestMapping(value = "/insertProducttype",method = RequestMethod.POST)
    public void insertProducttype(@RequestBody ProductType productType){
        productTypeService.insertProducttype(productType);
    }

    @RequestMapping(value = "/findProductytpeByid",method = RequestMethod.GET)
    public String findProductytpeByid(@RequestParam int id,Model model){
        ProductType productType = productTypeService.findProductytpeByid(id);
        model.addAttribute("productType",productType);
        return "producttypeview";

    }

    @RequestMapping(value = "/toupdateProductytpeByid",method = RequestMethod.GET)
    public String toupdateProductytpeByid(@RequestParam int id,Model model){
        ProductType productType = productTypeService.findProductytpeByid(id);
        model.addAttribute("productType",productType);
        return "producttypeupdate";
    }


    @RequestMapping(value = "/updateProductytpeByid",method = RequestMethod.POST)
    public void updateProductytpeByid(ProductType productType){
        productTypeService.updateProductytpeByid(productType);
    }



}
