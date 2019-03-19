package com.youfan.action;

import com.youfan.model.Product;
import com.youfan.model.ProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2018/7/29 0029.
 */
@Controller
public class indexAction {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        return "index";
    }
}
