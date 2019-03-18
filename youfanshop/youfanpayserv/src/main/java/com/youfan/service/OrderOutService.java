package com.youfan.service;
import com.youfan.model.Order;
import com.youfan.vo.OrderAll;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@FeignClient(value = "youfanorder")
public interface OrderOutService {

    @RequestMapping(value = "/insertOutOrder",method = RequestMethod.POST)
    public void insertOutOrder(@RequestBody OrderAll orderAll);

    @RequestMapping(value = "/findorderbyid",method = RequestMethod.POST)
    public Order findorderbyid(@RequestParam(value = "id") int id);

    @RequestMapping(value = "/updateorderstatebyid",method = RequestMethod.POST)
    public void updateorderstatebyid(@RequestParam(value = "id") int id,@RequestParam(value = "paytype") int paytype,@RequestParam(value = "paystatus") int paystatus);
}
