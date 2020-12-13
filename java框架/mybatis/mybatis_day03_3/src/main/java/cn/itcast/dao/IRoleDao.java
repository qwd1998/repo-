package cn.itcast.dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.User;

import java.util.List;

public interface IRoleDao {
    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> findAll();





    /**
     * 查询一个
     * @param rid
     * @return
     */
    Role findOne(int rid);




}
