package cn.itcast.dao;

import cn.itcast.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("AccountDao")
public interface AccountDao {
    /**
     * 查询账户所有
     * @return
     */
    @Select("select *from account")
    @Results(id="accountMap",
            value = {
                    @Result(id=true,property = "id",column = "id"),
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "money",column = "money"),
                    @Result(property = "user",column = "uid",one = @One(select = "cn.itcast.dao.UserDao.findOneUser",fetchType = FetchType.EAGER)),
            })
    public List<Account> findAllAccount();

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    @Select("select *from account where uid = #{uid} ")
    @ResultMap("accountMap")
    /*@Results( {
                    @Result(id=true,property = "id",column = "id"),
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "money",column = "money")
            })*/
    public Account findOneAccount(Integer id);



    /**
     * 修改信息
     * @param account
     * @return
     */
    @Update("update account set money = #{money} where id = #{id} ")
    void updateAccount(Account account);

    /**
     * 保存用户
     */
    @Insert("Insert into account(money) values(#{money})")
    void saveAccount(Account account);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from account where id = #{id}")
    void deleteAccount(int id);

    /**
     * 查询记录数
     * @return
     */
    @Select("Select count(*) from account")
    int CountAccount();

}
