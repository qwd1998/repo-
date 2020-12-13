package cn.itcast.dao;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PermissionDao")
public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId} )")
    public Permission findById(String id);

    @Select("select *from permission")
    public List<Permission> findAll();


    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);



    @Select("select *from permission where id = #{id}")
    @Results(value={
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id",many = @Many(select = "cn.itcast.dao.RoleDao.findByIdFromPermission"))
    })
    public Permission findOne(String id);

    @Delete("delete from permission where id = #{id} ")
    void delete(String id);
    @Delete("delete from role_permission where permissionId = #{id} ")
    void deleteRole_Permission(String id);
}


