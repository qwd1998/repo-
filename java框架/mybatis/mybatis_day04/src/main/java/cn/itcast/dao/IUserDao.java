package cn.itcast.dao;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户信息和用户所含的account信息
     *
     * @return
     */
    @Select(  "SELECT *from  user ")
    @Results(id = "userMap",
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "sex", column = "sex"),
                    @Result(property = "address", column = "address"),
                    @Result(property = "birthday", column = "birthday"),
                    @Result(property = "roles",column = "id",many = @Many(select = "cn.itcast.dao.IRoleDao.findOne",fetchType = FetchType.LAZY)),
                    @Result(property = "accounts",column = "id",many = @Many(select = "cn.itcast.dao.IAccountDao.findOne",fetchType = FetchType.LAZY))

            })
    List<User> findAll();


    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Select("SELECT u.* FROM USER u, user_role ur WHERE u.`id`=ur.`UID` AND ur.`RID` = #{rid}")
    @ResultMap(value = {"userMap"})
    User findOne(Integer id);

    /**
     * 更新用户
     *
     * @param user
     */
    @Update("update user set username=#{username},sex=#{sex} where id=#{id}")
    void updateUser(User user);

    /**
     * 保存用户
     *
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteUser(int id);

    /**
     * 模糊查询
     *
     * @param username
     * @return
     */
    @Select("Select *from user where username like #{username}")
    List<User> findLike(String username);


    /**
     * 查询条数
     *
     * @return
     */
    @Select("select count(*) from user")
    int findCount();
}
