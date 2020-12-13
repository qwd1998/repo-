package cn.itcast.mybatis.sqlsession.defaults;

import cn.itcast.mybatis.cfg.Configuration;
import cn.itcast.mybatis.sqlsession.SqlSession;
import cn.itcast.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory接口的实现类
 */
public class SqlSessionFactoryImpl implements SqlSessionFactory {
    private Configuration cfg;
    public SqlSessionFactoryImpl(Configuration cfg){
        this.cfg=cfg;
    }
    /**
     * 用于创建一个新的操作数据库对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new SqlSessionImpl(cfg);
    }
}
