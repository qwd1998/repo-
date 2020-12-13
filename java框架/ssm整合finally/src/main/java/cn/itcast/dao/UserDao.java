package cn.itcast.dao;

import cn.itcast.domain.Account;
import cn.itcast.domain.User;
import cn.itcast.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDao")
public interface UserDao {


    /**
     * 查询账户所有
     * @return
     */
    @Select("SELECT * from user ")
    @Results(id="userMap",value = {
            @Result(id = true,property ="id" ,column = "id"),
            @Result(property ="username" ,column = "username"),
            @Result(property ="birthday" ,column = "birthday"),
            @Result(property ="sex" ,column = "sex"),
            @Result(property ="address" ,column = "address"),
            @Result(property ="roles" ,column = "id",many = @Many(select = "cn.itcast.dao.RoleDao.findOneRole",fetchType = FetchType.LAZY)),
            @Result(property ="accounts" ,column = "id",many = @Many(select = "cn.itcast.dao.AccountDao.findOneAccount",fetchType = FetchType.LAZY))
    })
    public List<User> findAllUser();

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    @Select("SELECT u.* FROM USER u, user_role ur WHERE u.`id`=ur.`UID` AND ur.rid = #{rid} ")
    @ResultMap(value = "userMap")
   /* @Results( {
            @Result(id = true,property ="id" ,column = "id"),
            @Result(property ="username" ,column = "username"),
            @Result(property ="birthday" ,column = "birthday"),
            @Result(property ="sex" ,column = "sex"),
            @Result(property ="address" ,column = "address"),
    })*/
    public User findOneUser(Integer id);

    /**
     * 修改信息
     * @param user
     * @return
     */
    @Update("update user set username=#{username},sex=#{sex} where id=#{id}")
    void updateRole(User user);

    /**
     * 保存用户
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveRole(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteRole(int id);

    /**
     * 查询记录数
     * @return
     */
    @Select("Select count(*) from user")
    int CountRole();

}
