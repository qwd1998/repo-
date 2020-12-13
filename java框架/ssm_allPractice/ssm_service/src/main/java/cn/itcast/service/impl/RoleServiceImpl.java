package cn.itcast.service.impl;

import cn.itcast.dao.OrdersDao;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Orders;
import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import cn.itcast.service.OrdersService;
import cn.itcast.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;



    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
            roleDao.save(role);
    }

    @Override
    public void delete(String id) {
        roleDao.deleteRole_Permission(id);
        roleDao.deleteUser_Role(id);
        roleDao.delete(id);
    }

    @Override
    public List<Permission> findRoleNotPermission(String roleId) {
        return roleDao.findRoleNotPermission(roleId);
    }

    @Override
    public void saveRoleAndPermission(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.saveRoleAndPermission(roleId,permissionId);
        }

    }
}
