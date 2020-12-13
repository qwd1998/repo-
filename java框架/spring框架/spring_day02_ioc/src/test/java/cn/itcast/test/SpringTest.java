package cn.itcast.test;

import cn.itcast.Service.AccountService;
import cn.itcast.domain.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    AccountService as = (AccountService) ac.getBean("AccountService");

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
}
