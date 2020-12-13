package cn.itcast.service;

import cn.itcast.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 查询账户所有
     * @return
     */
    public List<User> findAllUser();

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    public User findOneUser(Integer id);

    /**
     * 修改信息
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 保存用户
     */
    void saveUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(int id);

    /**
     * 查询记录数
     * @return
     */
    int CountUser();

}
