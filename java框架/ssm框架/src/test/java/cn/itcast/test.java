package cn.itcast;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class test {
    @Autowired
    private AccountService as = null;

    @Test
    public void findAllAccount(){
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService as = (AccountService) ac.getBean("AccountService");
        as.findAll();
    }

    @Test
    public void findAllMybatis() throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建工厂SqlSessionFactoryBuilder
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3.使用factory创建SqlSession对象
        SqlSession sqlSession = factory.openSession(true);

        //4.使用sqlSession对象创建代理对象
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);

        //5.使用accountDao处理数据
        List<Account> list = accountDao.findAll();
        for (Account account : list) {
            System.out.println(account);
        }

        //6.释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void saveMybatis() throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建工厂SqlSessionFactoryBuilder
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3.使用factory创建SqlSession对象
        SqlSession sqlSession = factory.openSession(true);

        //4.使用sqlSession对象创建代理对象
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);

        //5.使用accountDao处理数据
        Account account = new Account();
        account.setName("都没写");
        account.setMoney(1999.9);
        accountDao.saveAccount(account);

        //6.释放资源
        sqlSession.close();
        in.close();
    }
}
