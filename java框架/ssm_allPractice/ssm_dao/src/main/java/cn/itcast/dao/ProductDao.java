package cn.itcast.dao;

import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductDao")
public interface ProductDao {

    @Select("select * from product")
    public List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Delete("delete from product where id = #{id}")
    void delete(String id);

    @Select("select * from product where id = #{id}")
    Product findOne(String id);

    @Update("update product set productNum=#{productNum} ,productName=#{productName} ,cityName=#{cityName} ,departureTime=#{departureTime} ,productPrice=#{productPrice} ,productDesc= #{productDesc},productStatus= #{productStatus} where id = #{id}")
    void update(Product product);
}
