package cn.itcast.mybatis.sqlsession.proxy;

import cn.itcast.mybatis.cfg.Mapper;
import cn.itcast.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy  implements InvocationHandler {

    //map的key是全限定类名＋方法名
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers,Connection conn){
        this.mappers=mappers;
        this.conn=conn;
    }

    /**
     * 用于对方法增强的，增强的就是selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.获取方法名
        String methodName = method.getName();

        //2.获取方法所在的类名称
        String classname = method.getDeclaringClass().getName();

        //3.组合key
        String key= classname +"."+methodName;

        //4.获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);

        //5.判断是否有值mapper
        if(mapper == null){
            throw new IllegalAccessException("传入的参数有误");
        }
        return new Executor().selectList(mapper,conn);
    }
}
