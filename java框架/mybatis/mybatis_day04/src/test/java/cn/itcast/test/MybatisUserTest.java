package cn.itcast.test;

import cn.itcast.dao.IUserDao;
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

public class MybatisUserTest {
    private SqlSession sqlSession;
    private InputStream in;
    private IUserDao userDao;

    @Before
    public void  init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取SqlSessionFactory对象生产factory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

         //3.使用SqlSession对象创建SqlSession对象
         sqlSession = factory.openSession(true);//自动提交

        //4.创建代理对象
         userDao = sqlSession.getMapper(IUserDao.class);
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
    public void findAllUser(){
        List<User> users = userDao.findAll();
        /*sqlSession.clearCache();
        for (User user : users) {
            System.out.println("-----------------------");
            System.out.println(user);
            System.out.println(user.getRoles());
            System.out.println(user.getAccounts());
        }*/
    }


    /**
     * Cache缓存，
     */
    @Test
    public void findOneUser(){
        User one = userDao.findOne(41);
        System.out.println("修改前查询"+one);
        System.out.println("修改前查询"+one.getAccounts());
  //      System.out.println(one.getRoles());

        /*one.setSex("男");
        one.setUsername("王富贵");
        userDao.updateUser(one);*/

        User three = userDao.findOne(41);
        System.out.println("修改后"+one);

        System.out.println(one==three);
    }

    /**
     * 保存用户信息
     */
    @Test
    public void saveUser(){
        User user = new User();
        user.setUsername("我是你爸爸");
        user.setAddress("安徽");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.saveUser(user);
    }

    /**
     * 删除用户
     */
    @Test
    public void deleteUser(){

        userDao.deleteUser(52);
    }


    /**
     * 根据用户名称模糊查询
     */
    @Test
    public void findLikeUser(){
        List<User> usersLike = userDao.findLike("%王%");
        for (User user : usersLike) {
            System.out.println(user);
        }

    }

    /**
     *查询记录数
     */
    @Test
    public void findCountUser(){
        int count = userDao.findCount();
        System.out.println(count);

    }

    @Test
    public void findAccountAndUser(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }


    }


}
