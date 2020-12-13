package cn.itcast.test;

import cn.itcast.dao.IUserDao;
import cn.itcast.domain.User;
import cn.itcast.domain.Vocd;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {
   private  InputStream in;
   private  SqlSessionFactoryBuilder builder;
   private  SqlSessionFactory factory;
   private  IUserDao userDao;
   private  SqlSession sqlSession;

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
    public void findAllTest(){
        //5.使用代理对象处理方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }



    /**
     * 查询一个用户信息
     * @throws IOException
     */
    @Test
    public void findOneTest() throws IOException {

        //5.使用代理对象处理方法
        User user = userDao.findOne(50);
        System.out.println(user);

    }



    /**
     * 模糊查询
     * @throws IOException
     */
    @Test
    public void findLikeTest() throws IOException {

        //5.使用代理对象处理方法
        List<User> users = userDao.findLike("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 在vocd中查询用户信息
     * @throws IOException
     */
    @Test
    public void findzTest() throws IOException {
        Vocd vocd = new Vocd();
        User user = new User();
        user.setUsername("老王");
        vocd.setUser(user);
        //5.使用代理对象处理方法
        List<User> findz = userDao.findz(vocd);
        System.out.println(findz);


    }

    @Test
    public void findUserByConditionTest() throws IOException {
        User u = new User();
        u.setUsername("%王%");
       // u.setSex("女");
        //5.使用代理对象处理方法
        List<User> users = userDao.findUserByCondition(u);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void findUserInIdsTest() throws IOException {
        Vocd vocd = new Vocd();
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(42);
        list.add(45);
        vocd.setIds(list);
        //5.使用代理对象处理方法
        List<User> users = userDao.findUserInIds(vocd);
        for (User user : users) {
            System.out.println(user);
        }

    }


}
