package com.youfan.service;
import com.youfan.vo.OrderAll;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@FeignClient(value = "youfanorder")
public interface OrderService {

    @RequestMapping(value = "/insertOutOrder",method = RequestMethod.POST)
    public  Integer insertOutOrder(@RequestBody OrderAll orderAll);
}
