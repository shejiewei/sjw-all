package com.youfan.dao;

import com.youfan.mapper.OrderDetailMapper;
import com.youfan.mapper.OrderMapper;
import com.youfan.model.Order;
import com.youfan.model.OrderDetail;
import com.youfan.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
@Component
public class OrderDetailDao {
    @Autowired
    OrderDetailMapper orderDetailMapper;

    public void insertOrderDetail(OrderDetail order){
        orderDetailMapper.insertOrderDetail(order);
    }
}
