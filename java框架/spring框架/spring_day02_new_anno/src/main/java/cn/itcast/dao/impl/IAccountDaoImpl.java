package cn.itcast.dao.impl;

import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.rmi.server.ExportException;
import java.sql.SQLException;
import java.util.List;

@Repository(value="IAccount")
@Scope(value = "prototype")
public class IAccountDaoImpl extends JdbcDaoSupport implements IAccountDao {

    @Autowired
    private JdbcTemplate template;
    //private JdbcTemplate template;


    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Account> findAll() {
        try {
            return template.query("select * from account",new BeanPropertyRowMapper<Account>(Account.class));
        } catch (Exception throwables) {
            return null;
        }

    }

    @Override
    public Account fineOne(int id) {
        try {
            return template.queryForObject("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),id);
        } catch (Exception throwables) {
            return null;
        }
    }

    @Override
    public Account findOne(String name) {
        try {
         return  template.queryForObject("select * from account where name= ? ",new BeanPropertyRowMapper<Account>(Account.class),name);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void updateAccount(Account account) {
        try {
             template.update("update account set name = ?, money=? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            template.update("insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAccount(int id) {
        try {
            template.update("delete from account where id = ?",id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int CountAccount() {
        try {
            return  template.queryForObject("select count(*) from account ",Integer.class);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }


}
