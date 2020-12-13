package cn.itcast.service.impl;

import cn.itcast.dao.OrdersDao;
import cn.itcast.domain.Orders;

import cn.itcast.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("OrdersService")
public class OrdertServiceImpl implements OrdersService {

    @Autowired
     private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) {
        //pageNum是页码值，pageSize是每页显示的条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findOne(String id) {
        return ordersDao.findOne(id);
    }

    @Override
    public void save(Orders orders) {
        ordersDao.save(orders);
    }

    @Override
    public void delete(String[] id) {
        for (String s : id) {
            ordersDao.delete(s);
        }

    }

    @Override
    public void update(Orders orders) {
            ordersDao.update(orders);
    }
}
