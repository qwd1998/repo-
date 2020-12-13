package cn.itcast.mybatis.sqlsession;

/**
 *
 */
public interface SqlSessionFactory {

    /**
     * 用于创建一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();

}
