package com.youfan.control;

import com.youfan.model.Order;
import com.youfan.model.OrderDetail;
import com.youfan.service.OrderDetailService;
import com.youfan.service.OrderService;
import com.youfan.vo.OrderAll;
import com.youfan.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
@RestController
public class OrderOutControl {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "/insertOutOrder",method = RequestMethod.POST)
    public Integer insertOrder(@RequestBody OrderAll orderAll){
        Order order = orderAll.getOrder();
        OrderDetail orderDetail = orderAll.getOrderDetail();
        orderService.insertOrder(order);
        orderDetail.setCreatetime(order.getCreatetime());
        orderDetail.setOrderid(order.getId());
        orderDetailService.insertOrderDetail(orderDetail);
        return order.getId();
    }

    @RequestMapping(value = "/findorderbyid",method = RequestMethod.POST)
    public Order findorderbyid(@RequestParam int id){
        Order order = orderService.findOrderByid(id);
        return order;
    }

    @RequestMapping(value = "/updateorderstatebyid",method = RequestMethod.POST)
    public void updateorderstatebyid(@RequestParam int id,@RequestParam int paytype,@RequestParam int paystatus){
        Order order = new Order();
        order.setId(id);
        order.setPaytype(paytype);
        order.setPaystatus(paystatus);
        orderService.updateOrderByid(order);
    }


}
