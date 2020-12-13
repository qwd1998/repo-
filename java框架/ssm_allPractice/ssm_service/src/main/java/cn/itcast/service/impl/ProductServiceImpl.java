package cn.itcast.service.impl;

import cn.itcast.domain.Product;
import cn.itcast.dao.ProductDao;
import cn.itcast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
     private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findOne(String id) {
        return productDao.findOne(id);
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            productDao.delete(id);
        }
    }

    @Override
    public void update(Product product) {
         productDao.update(product);
    }
}
