package cn.itcast.dao;

import cn.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    @Select(" select *from traveller where id in(select travellerid from ORDER_TRAVELLER where  orderid = #{id})")
    public List<Traveller> findOne(String id);
}
