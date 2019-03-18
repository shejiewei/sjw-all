package com.youfan.Control;

import com.youfan.model.*;
import com.youfan.service.OrderService;
import com.youfan.service.ProductService;
import com.youfan.service.ProductTypeService;
import com.youfan.vo.OrderAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@Controller
public class IndexControl {

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/indexproduct",method = RequestMethod.GET)
    public String sayHi(Model model , @RequestParam int productytpeid){
        List<ProductType> list = productTypeService.listproductType();
        model.addAttribute("productypelist",list);
        System.out.println(list);
        if(productytpeid == -1){
            productytpeid = list.get(0).getId();
        }
        List<Product> productlist = productService.listproduct(productytpeid);
        model.addAttribute("produclist",productlist);
        return "list";
    }

    @RequestMapping(value = "/searchproductout",method = RequestMethod.POST)
    public String searchproductout(Model model ,@RequestParam String keyword){
        List<Product> list = productService.searchproductout(keyword);
        model.addAttribute("produclist",list);
        return "list";
    }

    @RequestMapping(value = "/toproductdetail",method = RequestMethod.GET)
    public String sayHi(Model model , @RequestParam int productytpeid,@RequestParam int producid){
        List<ProductType> list = productTypeService.listproductType();
        model.addAttribute("productypelist",list);
        System.out.println(list);
        if(productytpeid == -1){
            productytpeid = list.get(0).getId();
        }
        Product product = productService.viewProduct(producid);
        model.addAttribute("product",product);

        ProductDetail productDetail = productService.findproductdetailbyproductid(producid);
        model.addAttribute("productDetail",productDetail);
        return "productdetail";
    }

    @RequestMapping(value = "/tobuy",method = RequestMethod.GET)
    public String tobuy(Model model,@RequestParam int productid, @RequestParam int num){
        List<ProductType> list = productTypeService.listproductType();
        model.addAttribute("productypelist",list);
        Product product = productService.viewProduct(productid);
        model.addAttribute("product",product);
        double price = product.getProductprice();//商品价格
        double totalamount = price*num;//总金额
        model.addAttribute("totalamount",totalamount);
        model.addAttribute("num",num);
        return "insertOrder";
    }

    @RequestMapping(value = "/insertOrder",method = RequestMethod.POST)
    public String insertOrder(Model model, HttpServletRequest req,int num,int productid,double payamount,int mechartid, String consigneeadress,String consigneename,String consigneephone){
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user == null){
            return "login";
        }else {
            Order order = new Order();
            User userreal = (User)user;
            int userid = userreal.getId();
            order.setUserid(userid);
            order.setPayamount(payamount);
            order.setConsigneename(consigneename);
            order.setConsigneephone(consigneephone);
            order.setConsigneeadress(consigneeadress);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductid(productid);
            orderDetail.setMechartid(mechartid);
            orderDetail.setTradenum(num);
            OrderAll orderAll = new OrderAll();
            orderAll.setOrder(order);
            orderAll.setOrderDetail(orderDetail);
            int orderid = orderService.insertOutOrder(orderAll);
            model.addAttribute("orderid",orderid);
            return "payorder";
        }


    }


    @RequestMapping(value = "/payOrder",method = RequestMethod.POST)
    public String payOrder(Model model, HttpServletRequest req,int orderid,int paytype){
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user == null){
            return "login";
        }else {
            if(1==paytype){//微信支付
                String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx705762491229630b&redirect_uri=http://youfan.natapp1.cc/auth?orderid="+orderid+"&response_type=code&scope=snsapi_base&connect_redirect=1#wechat_redirect";
                RestTemplate restTemplate = new RestTemplate();
                String response = restTemplate.getForObject(url, String.class);
                System.out.println(response);
            }
            return "list";
        }
    }



}
