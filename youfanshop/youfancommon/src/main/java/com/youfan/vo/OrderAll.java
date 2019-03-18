package com.youfan.vo;

import com.youfan.model.Order;
import com.youfan.model.OrderDetail;

/**
 * Created by Administrator on 2018/8/5 0005.
 */
public class OrderAll {
    private Order order;
    private  OrderDetail orderDetail;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
