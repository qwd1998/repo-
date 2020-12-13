package cn.itcast.test;

import cn.itcast.dao.IUserDao;
import cn.itcast.domain.User;
import cn.itcast.mybatis.io.Resources;
import cn.itcast.mybatis.sqlsession.SqlSession;
import cn.itcast.mybatis.sqlsession.SqlSessionFactory;
import cn.itcast.mybatis.sqlsession.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建工厂SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        //3.使用工厂生产SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //4.使用SqlSession对象创建Dao接口的代理对象
        IUserDao userDao = (IUserDao) sqlSession.getMapper(IUserDao.class);

        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();

        for (User user : users) {
            System.out.println(user);
        }

        //6.释放资源
        sqlSession.close();

    }
}
