package com.youfan.mapper;

import com.youfan.model.Order;
import com.youfan.vo.OrderVo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
public interface OrderMapper {
    public void insertOrder(Order order);
    public List<Order> listOrder(OrderVo orderVo);
    public void updateOrderWithUser(Order order);

    public Order findOrderByid(int id);

    public void deleteorderbyid(int id);

    public void updateOrderByid(Order order);
}
