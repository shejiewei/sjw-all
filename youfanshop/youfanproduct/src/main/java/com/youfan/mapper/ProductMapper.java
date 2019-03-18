package com.youfan.mapper;

import com.youfan.model.Product;
import com.youfan.vo.ProductVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/18 0018.
 */
public interface ProductMapper {
    public int insertProduct(Product product);
    public void auditProduct(Product product);
    public Product findproductbyid(int id);
    public void upateProduct(Product product);
    public void deleteProductbyid(int id);
    public List<Product> queryProductbyvo(ProductVo productVo);
    public void upateProductbyproductstatus(Product product);
    public List<Product> queryProductbyids(Map<String,List<String>> map);
}
