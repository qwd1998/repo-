package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.mybatis.annotations.Select;
//import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
}
