package cn.itcast.dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RoleDao")
public interface RoleDao {
    /**
     * 查询账户所有
     * @return
     */
    @Select("SELECT * from role")
    @Results(id="roleMap", value = {
            @Result(id=true,property ="roleId" ,column ="id"),
            @Result(property = "roleName",column="role_name"),
            @Result(property = "roleDesc",column="role_desc"),
            @Result(property = "users",column = "id",many = @Many(select = "cn.itcast.dao.UserDao.findOneUser",fetchType = FetchType.LAZY))

    }
    )
    public List<Role> findAllRole();

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    @Select("SELECT r.* FROM role r ,user_role  ur WHERE r.`ID`= ur.`RID` AND  ur.uid=#{uid} ")
    @ResultMap(value = "roleMap")
    /*@Results({
            @Result(id=true,property ="roleId" ,column ="id"),
            @Result(property = "roleName",column="role_name"),
            @Result(property = "roleDesc",column="role_desc"),
            //@Result(property = "users",column = "id",many = @Many(select = "cn.itcast.dao.UserDao.findOneUser",fetchType = FetchType.EAGER))
    })*/
    public Role findOneRole(Integer id);


    /**
     * 修改信息
     * @param role
     * @return
     */
    @Update("update role set username=#{username},sex=#{sex} where id=#{id}")
    void updateRole(Role role);

    /**
     * 保存用户
     */
    @Insert("Insert into Role(name,money) values(#{name},#{money})")
    void saveRole(Role role);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from Role where id = #{id}")
    void deleteRole(int id);

    /**
     * 查询记录数
     * @return
     */
    @Select("Select count(*) from Role")
    int CountRole();
}
