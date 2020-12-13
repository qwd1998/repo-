package cn.itcast.service;

import cn.itcast.domain.Orders;


import java.util.List;

public interface OrdersService {



    /**
     * 查询所有产品
     * @return
     */
    public List<Orders> findAll(int page,int size);

    public Orders findOne(String id);

    public void save (Orders orders);


    public void delete(String[] id);

    void update(Orders orders);
}
