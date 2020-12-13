package cn.itcast.oracle;

import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.*;

public class OracleDemo1 {

    @Test
    public void javaOracle() throws Exception {
        //1.加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //2.得到Connection连接
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "itcast", "itcast");

        //3.得到预编译的prepareStatement对象
        PreparedStatement pstm = conn.prepareStatement("select * from emp where empno = ? ");
        pstm.setInt(1,7788);

        //4.获得执行sql的
        ResultSet rs = pstm.executeQuery();

        //输出结果
        while (rs.next()){
            System.out.println(rs.getString("ename"));
        }
        rs.close();
        pstm.close();
        conn.close();


    }

    /**
     * java调用存储过程
     *
     *    {?= call <procedure-name>[(<arg1>,<arg2>, ...)]} 调用存储函数
     *    {call <procedure-name>[(<arg1>,<arg2>, ...)]} 调用存储过程
     * @throws Exception
     */
    @Test
    public void javaProcedure() throws Exception {
        //1.加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //2.得到Connection连接
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "itcast", "itcast");

        //3.得到预编译的prepareStatement对象
        CallableStatement pstm =  conn.prepareCall("{call p_yearsal(?,?)} ");
        pstm.setInt(1,7788);
        pstm.registerOutParameter(2, OracleTypes.NUMBER);

        //4.获得执行sql的
        pstm.execute();

        //5.输出结果，第二个参数
        System.out.println(pstm.getObject(2));
        pstm.close();
        conn.close();


    }

    @Test
    public void javaFunction() throws Exception {
        //1.加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //2.得到Connection连接
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "itcast", "itcast");

        //3.得到预编译的prepareStatement对象
        CallableStatement pstm =  conn.prepareCall("{?=call f_yearsal(?)} ");
        pstm.setInt(2,7788);
        pstm.registerOutParameter(1, OracleTypes.NUMBER);

        //4.获得执行sql的
        pstm.execute();

        //5.输出结果，第二个参数
        System.out.println(pstm.getObject(1));
        pstm.close();
        conn.close();


    }
}
