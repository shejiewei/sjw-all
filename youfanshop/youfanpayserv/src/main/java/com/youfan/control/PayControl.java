package com.youfan.control;

import com.alibaba.fastjson.JSONObject;
import com.youfan.model.Order;
import com.youfan.service.OrderOutService;
import com.youfan.service.impl.WeiXinPayService;
import com.youfan.service.impl.YinLianPayService;
import com.youfan.service.impl.ZhifuBaoPayService;
import com.youfan.service.weixin.PayRequest;
import com.youfan.service.weixin.PayResponse;
import com.youfan.service.weixin.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
@Controller
@Slf4j
public class PayControl {
    @Autowired
    WeiXinPayService weiXinPayService;
    @Autowired
    YinLianPayService yinLianPayService;
    @Autowired
    ZhifuBaoPayService zhifuBaoPayService;
    @Autowired
    OrderOutService orderService;

    /**
     *
     * @param paytype 1 微信支付 2 支付宝支付 3 银联卡支付
     * @param tradenumber
     * @param amount
     * @return 1支付成功 2支付失败
     */
    @RequestMapping(value = "payWithAmout")
    public int payWithAmout(String paytype,String tradenumber,int amount){
        int payresult = 2;
        if("1".equals(paytype)){
            payresult = weiXinPayService.payWithpayAmount(tradenumber,amount);
        }else if("2".equals(paytype)){
            payresult = yinLianPayService.payWithpayAmount(tradenumber,amount);
        }else if("3".equals(paytype)){
            payresult =zhifuBaoPayService.payWithpayAmount(tradenumber,amount);
        }
        return payresult;
    }

    @GetMapping("/auth")
    public ModelAndView auth(@RequestParam("code") String code,@RequestParam("orderid") int orderid) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx705762491229630b&redirect_uri=http://youfan.natapp1.cc/auth?orderid=2&response_type=code&scope=snsapi_base#wechat_redirect
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx705762491229630b&secret=f984414914131383e75cd5741371618f&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
        JSONObject object = JSONObject.parseObject(response);
        String openid = object.getString("openid");

        WxPayServiceImpl wxPayServiceImpl = new WxPayServiceImpl();
        PayRequest request = new PayRequest();
        request.setOrderName("友凡测试支付");//订单名字
        //订单查询
        request.setOrderId(orderid+"");//订单号
        Order order = orderService.findorderbyid(orderid);
        request.setOrderAmount(order.getPayamount());//支付金额
        request.setOpenid(openid);//微信openid, 仅微信支付时需要

        PayResponse payResponse = wxPayServiceImpl.pay(request);

        log.info("====="+openid);
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("payResponse", payResponse);
        map.put("returnUrl", "http://youfan.natapp1.cc/notify");
        return new ModelAndView("pay/create", map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
//        payService.notify(notifyData);
        WxPayServiceImpl wxPayServiceImpl = new WxPayServiceImpl();
        PayResponse payResponse = wxPayServiceImpl.asyncNotify(notifyData);
        if(payResponse != null && payResponse.getOrderId() != null){
                String orderid = payResponse.getOrderId();
            orderService.updateorderstatebyid(Integer.valueOf(orderid),1,2);
        }
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
