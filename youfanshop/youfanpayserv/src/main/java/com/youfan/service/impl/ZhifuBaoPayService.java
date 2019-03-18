package com.youfan.service.impl;

import com.youfan.service.PayService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/6/23 0023.
 */
@Service
public class ZhifuBaoPayService implements PayService {
    /**
     * 0支付成功，1支付异常，不成功
     * @param tradenumber
     * @param amount
     * @return
     */
    public int payWithpayAmount(String tradenumber,int amount){
        System.out.println("支付宝支付成功");
        return 0;
    }
}
