package cn.itcast.dao;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RoleDao")

public interface RoleDao {

    /**
     * 根据user的id在中间表查出role的信息
     * @param id
     * @return
     */
    @Select("select *from role where id in (select roleid from users_role where userid = #{id} )")
    @Results(id = "RoleMap",value = {
            @Result(id = true,property ="id" ,column = "id"),
            @Result(property ="roleName" ,column = "roleName"),
            @Result(property ="roleDesc" ,column = "roleDesc"),
            @Result(property ="permissions" ,column = "id",many = @Many(select = "cn.itcast.dao.PermissionDao.findById")),
    })
    public List<Role> findOne(String id);

    /**
     * 根据id查询role，并且在permissionDao中查询出该role包含的permission的信息
     * @param id
     * @return
     */
    @Select("select *from  role where id = #{id}")
    @ResultMap("RoleMap")
    Role findById(String id);


    /**
     * 查询所有的role和角色包含的permission和users
     * @return
     */
    @Select("select *from role ")
    @Results(value = {
            @Result(id = true,property ="id" ,column = "id"),
            @Result(property ="roleName" ,column = "roleName"),
            @Result(property ="roleDesc" ,column = "roleDesc"),
            @Result(property ="permissions" ,column = "id",many = @Many(select = "cn.itcast.dao.PermissionDao.findById")),
            @Result(property ="users" ,column = "id",many = @Many(select = "cn.itcast.dao.UserDao.findOne"))
    })
    List<Role> findAll();


    /**
     * 添加一个role
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 删除role
     * @param id
     */

    @Delete("delete from role where id = #{id} ")
    void delete(String id);

    @Delete("delete from role_permission where roleId = #{id} ")
    void deleteRole_Permission(String id);

    @Delete("delete from users_role where roleId = #{id} ")
    void deleteUser_Role(String id);


    /**
     * 根据permission的id在中间表查询role
     * @param id
     * @return
     */
    @Select("select *from role where id in (select roleId from role_permission where permissionId = #{id} )")
    @Results(value = {
            @Result(id = true,property ="id" ,column = "id"),
            @Result(property ="roleName" ,column = "roleName"),
            @Result(property ="roleDesc" ,column = "roleDesc"),
    })
    public List<Role> findByIdFromPermission(String id);

    @Select("select *from permission where id not in (select permissionId from role_permission where roleId=#{roleId} )")
    List<Permission> findRoleNotPermission(String roleId);


    @Insert("insert into role_permission(permissionId,roleId) values( #{permissionId},#{roleId} )")
    void saveRoleAndPermission(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
