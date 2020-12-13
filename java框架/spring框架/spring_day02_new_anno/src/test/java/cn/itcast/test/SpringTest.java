package cn.itcast.test;

import cn.itcast.Service.AccountService;
import cn.itcast.domain.Account;
import config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * junit和spring整合
 * 1.导入spring-text的坐标
 * 2.junit版本为4.12及其以上
 * 3.使用RunWith注解 指定SpringJUnit4ClassRunner.class
 * 4.使用ContextConfiguration注解 locations指定xml文件  classes指定配置类
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringTest {
    //使用xml配置的
   // ApplicationContext ac = new ClassPathXmlApplicationContext("bean2.xml");

    //使用注解配置的

    /*ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    AccountService as = (AccountService) ac.getBean("AccountService");*/
    @Autowired
    private AccountService as = null;

    @Test
    public void findAllAccount(){
        List<Account> accounts = as.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void findOneAccount(){
        Account account = as.fineOne(2);
        System.out.println(account);
    }

    @Test
    public void saveAccount(){
        Account account = new Account();
        account.setName("dsn");
        account.setMoney(127);
        as.saveAccount(account);
    }

    @Test
    public void updateAccount(){
        Account account = new Account();
        account.setId(4);
        account.setName("aadss");
        account.setMoney(1999);
        as.updateAccount(account);
    }

    @Test
    public void deleteAccount(){
        as.deleteAccount(5);
    }

    @Test
    public void CountAccount(){
        int i = as.CountAccount();
        System.out.println(i);
    }
}
