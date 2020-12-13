package cn.itcast.service;

import cn.itcast.domain.Role;

import java.util.List;

public interface RoleService {
    /**
     * 查询账户所有
     * @return
     */
    public List<Role> findAllRole();

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    public Role findOneRole(Integer id);

    /**
     * 修改信息
     * @param role
     * @return
     */
    void updateRole(Role role);

    /**
     * 保存用户
     */
    void saveRole(Role role);

    /**
     * 删除用户
     * @param id
     */
    void deleteRole(int id);

    /**
     * 查询记录数
     * @return
     */
    int CountRole();
}
