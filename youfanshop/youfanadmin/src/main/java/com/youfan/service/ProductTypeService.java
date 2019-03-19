package com.youfan.service;
import com.youfan.model.ProductType;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
@FeignClient(value = "youfanproducttype")
public interface ProductTypeService {
    @RequestMapping(value = "/listproducttype",method = RequestMethod.GET)
    List<ProductType> listproductType();


    @RequestMapping(value = "/insertoutProducttype",method = RequestMethod.POST)
    public void insertProducttype(@RequestBody ProductType productType);

    @RequestMapping(value = "/findoutProductytpeByid",method = RequestMethod.GET)
    public ProductType findProductytpeByid(@RequestParam(value = "id") int id);


    @RequestMapping(value = "/updateoutProductytpeByid",method = RequestMethod.POST)
    public void updateProductytpeByid(@RequestBody ProductType productType);

}
