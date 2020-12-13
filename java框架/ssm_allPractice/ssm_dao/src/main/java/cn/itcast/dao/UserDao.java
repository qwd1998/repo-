package cn.itcast.dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDao")

public interface UserDao {

    @Select("select *from users where username = #{username} ")
    @Results(id = "userMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "status",column = "status"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "roles",column = "id",many = @Many(select = "cn.itcast.dao.RoleDao.findOne"),javaType = java.util.List.class),
    })
    public UserInfo findByUsername(String username);

    @Select("select *from users")
    @ResultMap("userMap")
    List<UserInfo> findAll();


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select *from users where id = #{id} ")
    @ResultMap("userMap")
    UserInfo findById(String id);


    /*@Select("select *from users where id = #{id} ")
    UserInfo findById2(String id);
*/

    @Select("select *from users where id in ( select userId from users_role where roleId = #{id}) ")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "status",column = "status"),
            @Result(property = "phoneNum",column = "phoneNum"),
          //  @Result(property = "roles",column = "id",many = @Many(select = "cn.itcast.dao.RoleDao.findOne"),javaType = java.util.List.class),
    })
    UserInfo findOne(String id);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void findUserByIdAndAllRole(@Param("userId")String userId,@Param("roleId")String roleId);


    @Delete("delete from users where id = #{id} ")
    void delete(String id);
    @Delete("delete from users_role where userId = #{id} ")
    void deleteUser_Role(String id);


    @Select("select *from role where id not in (select roleId from users_role where userId = #{userId}) ")
    List<Role> findNotRole(String userId);
}
