package cn.itcast.dao;

import cn.itcast.domain.User;



import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户信息和用户所含的account信息
     * @return
     */
    List<User> findAll();





    /**
     * 查询一个
     * @param id
     * @return
     */
    User findOne(int id);




}
