package cn.itcast.test;

import cn.itcast.dao.IAccountDao;
import cn.itcast.dao.IRoleDao;
import cn.itcast.domain.Role;
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

public class MybatisRoleTest {
    private SqlSession sqlSession;
    private InputStream in;
    private IRoleDao roleDao;

    @Before
    public void  init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取SqlSessionFactory对象生产factory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

         //3.使用SqlSession对象创建SqlSession对象
         sqlSession = factory.openSession(true);//自动提交

        //4.创建代理对象
         roleDao = sqlSession.getMapper(IRoleDao.class);
    }

    @After
     public void destory() throws IOException {
        //6.释放资源
        sqlSession.close();
        in.close();

    }


    /**
     *根据查询所有角色并查询用户信息
     */

    @Test
    public void findAllRole(){
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println("-----------------------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

    @Test
    public void findOneRole(){
        Role role = roleDao.findOne(1);

            System.out.println("-----------------------");
            System.out.println(role);
           // System.out.println(role.getUsers());

    }




}
