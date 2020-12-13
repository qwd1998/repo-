package cn.itcast;

import cn.itcast.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class test {
    @Autowired
    private AccountService as;

    @Test
    public void trans(){
       /* ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService as= (AccountService) ac.getBean("AccountService");*/

        as.trans(42,45,-500);
    }
}
