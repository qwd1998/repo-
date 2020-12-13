package cn.itcast.utils;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，用于从数据源中获取一个连接，并且实现和线程的绑定
 *
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> t1 = new ThreadLocal<>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     */
    public Connection getThreadConnection() throws SQLException {
        //1.先从ThreadLocal上获取
        Connection conn=t1.get();

        //2.判断当前线程上是否有连接
        if (conn == null){
            //3.从数据源中获取一个连接，并且存入ThreadLocal中
            conn=dataSource.getConnection();
            t1.set(conn);
        }
        return conn;
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        t1.remove();
    }
}
