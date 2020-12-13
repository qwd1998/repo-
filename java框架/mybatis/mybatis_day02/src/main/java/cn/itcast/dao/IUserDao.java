package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.domain.Vocd;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户信息
     * @param user
     */
    void saveUser(User user);

    /**
     * 查询一个
     * @param id
     * @return
     */
    User findOne(int id);


    /**
     * 删除一个用户
     * @param id
     */
    void deleteUser(int id);

    /**
     * 修改一个用户
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 通过模糊查询
     * @param username
     * @return
     */
    List<User> findLike(String username);

    /**
     * 查询条数
     * @return
     */
    int findCount();

    /**
     * 在Vocd中查询user对象
     * @param vocd
     * @return
     */
    List<User> findz(Vocd vocd);

}
