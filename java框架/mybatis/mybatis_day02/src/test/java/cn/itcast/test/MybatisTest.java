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
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的CRUD
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;


    /**
     * 测试查询所有
     *
     * @throws IOException
     */
    @Test
    public void TestfindAll() throws IOException {

        //5.使用代理对象处理方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }


    /**
     * 测试方法执行前执行
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        //1.读取配置文件
         in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建SqlSessionFactory工厂类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        //3.使用SqlSessionFactory工厂类创建SqlSession对象
         sqlSession = factory.openSession();

        //4.使用SqlSession对象创建代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 测试类执行完成后，执行，释放资源
     * @throws IOException
     */
    @After
    public  void destory() throws IOException {
        //提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 保存用户信息
     */
    @Test
    public void Testsave() throws IOException {
        User user = new User();
        user.setUsername("德玛");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("海南");
        System.out.println("保存前："+user);


        //5.使用代理对象处理方法
        userDao.saveUser(user);
        System.out.println("保存后："+user);
    }

    /**
     * 查询一个用户信息
     * @throws IOException
     */
    @Test
    public void TestfindOne() throws IOException {

        //5.使用代理对象处理方法
        User user = userDao.findOne(50);
        System.out.println(user);

    }

    /**
     * 根据id删除用户
     * @throws IOException
     */
    @Test
    public void TestdeleteUser() throws IOException {

        //5.使用代理对象处理方法
        userDao.deleteUser(50);

    }

    /**
     * 根据对象更新用户
     * @throws IOException
     */
    @Test
    public void TestupdateUser() throws IOException {
        User user = new User();
        user.setId(51);
        user.setUsername("诺克萨斯");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("广州");

        //5.使用代理对象处理方法
        userDao.updateUser(user);
    }


    /**
     * 模糊查询
     * @throws IOException
     */
    @Test
    public void TestfindLike() throws IOException {

        //5.使用代理对象处理方法
        List<User> users = userDao.findLike("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询记录数
     * @throws IOException
     */
    @Test
    public void TestfindCount() throws IOException {

        //5.使用代理对象处理方法
        int count = userDao.findCount();
        System.out.println(count);

    }


    /**
     * 在vocd中查询用户信息
     * @throws IOException
     */
    @Test
    public void Testfindz() throws IOException {
        Vocd vocd = new Vocd();
        User user = new User();
        user.setUsername("老王");
        vocd.setUser(user);
        //5.使用代理对象处理方法
        List<User> findz = userDao.findz(vocd);
        System.out.println(findz);


    }
}
