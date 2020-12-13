package cn.itcast.mybatis.sqlsession;

/**
 * 自定义Mybatis中和数据库交互的核心类，
 * 可以创建dao接口的代理对象
 */
public interface SqlSession {
    /**
     * 根据参数创建dao的代理对象
     * @param <T>
     * @param daoInterfaceClass dao接口的字节码文件
     * @return
     */
    <T> Object getMapper(Class<T> daoInterfaceClass);


    /**
     * 释放资源
     */
    void close();
}
