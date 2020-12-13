package cn.itcast.test;

import cn.itcast.dao.IAccountDao;
import cn.itcast.dao.IUserDao;
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

import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory;
    private IUserDao userDao;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        //1.加载配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取工厂类SqlSessionFactory
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);

        //3.生产SqlSession对象
        sqlSession = factory.openSession(true);

        //4.使用SqlSession获取代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destory() throws IOException {
        //6.提交事务
        // sqlSession.commit();

        //7.释放资源
        sqlSession.close();
        in.close();

    }


    /**
     * 查询所有用户信息并且查询用户所拥有的账户信息
     */
    @Test
    public void findAllTest() {
        //5.使用代理对象处理方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }


    /**
     * 查询一个用户信息,
     *
     * @throws IOException
     */
    @Test
    public void findOneTest() throws IOException {

        //5.使用代理对象处理方法
        User user = userDao.findOne(41);
        System.out.println(user);
        System.out.println(user.getAccounts());

    }

    /**
     * 一对一的关系查询，把user存入account对象中
     * @throws IOException
     */
    @Test
    public void findAll() throws IOException {

        //5.使用代理对象处理方法
        List<Account> list = accountDao.findAll();
        for (Account account : list) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }



    /**
     * 根据uid查询和account表的信息
     * @throws IOException
     */
    @Test
    public void findAccountByUid() throws IOException {

        //5.使用代理对象处理方法
        List<Account> accounts = accountDao.findByUid(41);
        for (Account account : accounts) {
            System.out.println(account);
        }
    }


}



