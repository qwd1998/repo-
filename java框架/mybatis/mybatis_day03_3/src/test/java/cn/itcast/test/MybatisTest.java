package cn.itcast.test;

import cn.itcast.dao.IRoleDao;
import cn.itcast.dao.IUserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IRoleDao roleDao;
    private IUserDao userDao;


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
        roleDao = sqlSession.getMapper(IRoleDao.class);

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
     * 查询所有用户信息
     */
    @Test
    public void findAllRoleTest() {
        //5.使用代理对象处理方法
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

    @Test
    public void findAllUserTest() {
        //5.使用代理对象处理方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }

    /**
     * 查询一个用户信息
     *
     * @throws IOException
     */
    @Test
    public void findOneTest() throws IOException {

        //5.使用代理对象处理方法
        User user = userDao.findOne(41);
        System.out.println(user);

    }
    @Test
    public void findOneTest1() throws IOException {

        //5.使用代理对象处理方法
        Role role1 = roleDao.findOne(1);

        System.out.println(role1);

    }



}



