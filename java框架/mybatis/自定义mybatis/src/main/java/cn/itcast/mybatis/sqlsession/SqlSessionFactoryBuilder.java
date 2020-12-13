package cn.itcast.mybatis.sqlsession;

import cn.itcast.mybatis.cfg.Configuration;
import cn.itcast.mybatis.sqlsession.defaults.SqlSessionFactoryImpl;
import cn.itcast.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建SqlSession对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg  = XMLConfigBuilder.loadConfiguration(config);

        return new SqlSessionFactoryImpl(cfg);
    }
}
