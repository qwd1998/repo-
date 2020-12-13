package cn.itcast.dao;

import cn.itcast.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询account表中的所有数据,并且获取每个账户下的用户信息
     * @return
     */
    @Select("select *from  account")
    @Results(id="accountMap",
        value = {
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "money",column = "money"),
            @Result(property = "user",column = "uid",one = @One(select = "cn.itcast.dao.IUserDao.findOne",fetchType = FetchType.EAGER)),
        })
    List<Account> findAll();


    @Select("select *from account where uid=#{uid} ")
    @ResultMap(value={"accountMap"})
    Account findOne(Integer id);
}
