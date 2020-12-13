package cn.itcast.dao.impl;

import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;

import cn.itcast.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;
import java.rmi.server.ExportException;
import java.sql.SQLException;
import java.util.List;

@Repository("AccountDao")
public class IAccountDaoImpl implements IAccountDao {

    @Autowired
    private ConnectionUtils connectionUtils;
    //private JdbcTemplate template;

    @Autowired
    private QueryRunner runner;



    @Override
    public List<Account> findAll() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account", new BeanListHandler<Account>(Account.class));
        } catch (Exception throwables) {
            return null;
        }

    }

    @Override
    public Account fineOne(int id) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account where id = ?",new BeanHandler<Account>(Account.class),id);
        } catch (Exception throwables) {
            return null;
        }
    }

    @Override
    public Account findOne(String name) {
        try {
         return  runner.query(connectionUtils.getThreadConnection(),"select * from account where name= ? ",new BeanHandler<Account>(Account.class),name);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"update account set name = ?, money=? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAccount(int id) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"delete from account where id = ?",id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
