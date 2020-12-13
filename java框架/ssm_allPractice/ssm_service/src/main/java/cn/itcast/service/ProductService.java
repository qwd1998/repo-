package cn.itcast.service;

import cn.itcast.domain.Product;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有产品
     * @return
     */
    public List<Product> findAll();

    public Product findOne(String id);


    /**
     * 保存产品
     * @param product
     */
    public void save (Product product);

    public void delete(String[] ids);

    void update(Product product);


}
