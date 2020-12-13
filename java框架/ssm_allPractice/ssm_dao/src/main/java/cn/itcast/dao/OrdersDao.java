package cn.itcast.dao;

import cn.itcast.domain.Orders;
import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OrdersDao")
public interface OrdersDao {
    @Select("select * from orders")
    @Results(id = "OrdersMap",value = {
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "cn.itcast.dao.ProductDao.findOne")),
            @Result(property = "travellers",column = "id",many = @Many(select = "cn.itcast.dao.TravellerDao.findOne")),
            @Result(property = "member",column = "memberId",one = @One(select = "cn.itcast.dao.MemberDao.findOne"))
    })
    public List<Orders> findAll();


    void save(Orders orders);

    @Delete("delete from orders where id = #{id}")
    void delete(String id);

    @Select("select * from orders where id = #{id}")
    @ResultMap("OrdersMap")
    Orders findOne(String id);

    void update(Orders orders);
}
