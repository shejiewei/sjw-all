package com.youfan.control;

import com.youfan.model.Order;
import com.youfan.service.OrderService;
import com.youfan.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
@Controller
public class OrderControl {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/toinsertOrder",method = RequestMethod.GET)
    public String toinsertOrder(){
        return "insertOrder";
    }

    @RequestMapping(value = "/listOrder",method = RequestMethod.GET)
    public String listOrder(OrderVo orderVo,Model model){
        List<Order> list = orderService.listOrder(orderVo);
        model.addAttribute("orderlist",list);
        return "listorder";
    }

    @RequestMapping(value = "/insertOrder",method = RequestMethod.POST)
    public void insertOrder(Order order){
        orderService.insertOrder(order);
    }

    @RequestMapping(value = "/toupdateOrderInfo",method = RequestMethod.GET)
    public String toupdateOrderInfo(Model model,int id){
        Order order = orderService.findOrderByid(id);
        model.addAttribute("order",order);
        return "updateOrder";
    }

    @RequestMapping(value = "/updateOrderInfo",method = RequestMethod.POST)
    public void updateOrderInfo(int id,String consigneeadress,String consigneephone,String consigneename){

        orderService.updateOrderWithUser(id,consigneeadress,consigneephone,consigneename);
    }

    @RequestMapping(value = "/toviewOrder",method = RequestMethod.GET)
    public String toinsertOrder(Model model,int id){
        Order order = orderService.findOrderByid(id);
        model.addAttribute("order",order);
        return "viewOrder";
    }


    @RequestMapping(value = "/toupdateOrderInfoWithMechart",method = RequestMethod.GET)
    public String toupdateOrderInfoWithMechart(Model model,int id){
        Order order = orderService.findOrderByid(id);
        model.addAttribute("order",order);
        return "updateOrderWithMechart";
    }

    @RequestMapping(value = "/updateOrderInfoWithMechart",method = RequestMethod.POST)
    public void updateOrderInfoWithMechart(int id,String consigneeadress,String consigneephone,String consigneename){

        orderService.updateOrderWithUser(id,consigneeadress,consigneephone,consigneename);
    }

    @RequestMapping(value = "/deleteorderbyid",method = RequestMethod.GET)
    public void deleteorderbyid(int id){
        orderService.deleteorderbyid(id);
    }

}
