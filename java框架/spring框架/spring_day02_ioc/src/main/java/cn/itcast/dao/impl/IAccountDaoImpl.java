package cn.itcast.dao.impl;

import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository(value="IAccount")
public class IAccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;
    //private JdbcTemplate template;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    @Override
    public List<Account> findAll() {
        try {
            return runner.query("select * from account",new BeanListHandler<Account>(Account.class));
        } catch (SQLException throwables) {
            return null;
        }

    }

    @Override
    public Account fineOne(int id) {
        try {
            return runner.query("select * from account where id = ?",new BeanHandler<Account>(Account.class),id);
        } catch (SQLException throwables) {
            return null;
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
             runner.update("update account set name = ?, money=? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update("insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAccount(int id) {
        try {
            runner.update("delete from account where id = ?",id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
