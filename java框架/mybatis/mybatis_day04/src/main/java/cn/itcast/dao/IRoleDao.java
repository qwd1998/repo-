package cn.itcast.dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IRoleDao {
    /**
     * 查询所有角色信息
     * @return
     */
    @Select("SELECT *from role")
    @Results(id="roleMap"
    ,value = {
            @Result(id=true,property = "roleId",column="id"),
            @Result(property = "roleName",column="role_name"),
            @Result(property = "roleDesc",column="role_desc"),
            @Result(property = "users",column = "id",many = @Many(select = "cn.itcast.dao.IUserDao.findOne",fetchType = FetchType.LAZY))
    })
    List<Role> findAll();





    /**
     * 查询一个
     * @param rid
     * @return
     */
    @Select("SELECT r.* FROM role r ,user_role  ur WHERE r.`ID`= ur.`RID` AND  ur.uid=#{uid}")
    //@ResultMap(value={"roleMap"})
    @Results({
            @Result(id=true,property = "roleId",column="id"),
            @Result(property = "roleName",column="role_name"),
            @Result(property = "roleDesc",column="role_desc"),
           // @Result(property = "users",column = "id",many = @Many(select = "cn.itcast.dao.IUserDao.findOne",fetchType = FetchType.EAGER))
    })
    Role findOne(Integer rid);




}
