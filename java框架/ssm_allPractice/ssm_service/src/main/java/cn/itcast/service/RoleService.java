package cn.itcast.service;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface RoleService  {

    Role findById(String id);


    List<Role> findAll(int page,int size);

    void save(Role role);


    void delete(String id);

    List<Permission> findRoleNotPermission(String roleId);

    void saveRoleAndPermission(String roleId, String[] permissionIds);
}
