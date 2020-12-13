package cn.itcast.dao;

import cn.itcast.domain.Account;
import cn.itcast.domain.User;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询account表的所有信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询和account表有关的所有人的信息
     * @return
     */
    List<User> findAllAccount();

    /**
     * 根据用户id查询account信息
     * @param uid
     * @return
     */
    List<Account> findByUid(int uid);


}
