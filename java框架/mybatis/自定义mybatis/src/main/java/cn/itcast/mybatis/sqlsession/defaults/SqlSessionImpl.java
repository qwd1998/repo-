package cn.itcast.mybatis.sqlsession.defaults;

import cn.itcast.mybatis.cfg.Configuration;
import cn.itcast.mybatis.sqlsession.SqlSession;
import cn.itcast.mybatis.sqlsession.proxy.MapperProxy;
import cn.itcast.mybatis.utils.DataSourceUtil;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession的实现类
 */
public class SqlSessionImpl implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public SqlSessionImpl(Configuration cfg) {
        this.cfg = cfg;
        connection= DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     *
     * @param daoInterfaceClass dao接口的字节码文件
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {

        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 用于释放资源
     */
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
