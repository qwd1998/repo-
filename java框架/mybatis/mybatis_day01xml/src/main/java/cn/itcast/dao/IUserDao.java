package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

/**
 * 用户持久层操作
 */
public interface IUserDao {
    /**
     * 查询所有用户的操作
     * @return
     */
    List<User> findAll();
}
