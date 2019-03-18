package com.youfan.service;

import com.youfan.dao.OrderDao;
import com.youfan.dao.OrderDetailDao;
import com.youfan.model.Order;
import com.youfan.model.OrderDetail;
import com.youfan.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    public void insertOrderDetail(OrderDetail order){
        orderDetailDao.insertOrderDetail(order);
    }
}
