package cn.itcast.test;

import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;
import cn.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisAccountTest {
    private SqlSession sqlSession;
    private InputStream in;
    private IAccountDao accountDao;

    @Before
    public void  init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取SqlSessionFactory对象生产factory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

         //3.使用SqlSession对象创建SqlSession对象
         sqlSession = factory.openSession(true);//自动提交

        //4.创建代理对象
         accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
     public void destory() throws IOException {
        //6.释放资源
        sqlSession.close();
        in.close();

    }


    /**
     *
     */
    @Test
    public void findAllAccount(){
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("-----------------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }




}
