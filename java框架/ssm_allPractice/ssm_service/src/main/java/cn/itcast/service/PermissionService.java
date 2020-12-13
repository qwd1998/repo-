package cn.itcast.service;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;

import java.util.List;

public interface PermissionService {

    Permission findById(String id);


    List<Permission> findAll(int page, int size);

    void save(Permission permission);

    public Permission findOne(String id);

    public void delete(String id);
}
