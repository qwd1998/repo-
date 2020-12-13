package cn.itcast.test;

import cn.itcast.Service.AccountService;
import cn.itcast.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean2.xml")
public class SpringTest {
    @Autowired
    private AccountService as ;

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
