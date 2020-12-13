package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.domain.Vocd;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;


import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();





    /**
     * 查询一个
     * @param id
     * @return
     */
    User findOne(int id);



    /**
     * 通过模糊查询
     * @param username
     * @return
     */
    List<User> findLike(String username);


    @Results(value = {

    })
    /**
     * 在Vocd中查询user对象
     * @param vocd
     * @return
     */
    List<User> findz(Vocd vocd);

    /**
     * 根基条件查询用户
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    List<User> findUserInIds(Vocd vocd);

    User tj(int id);
}
