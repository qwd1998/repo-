package cn.itcast.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Client {
    /**
     * 基于子类的动态代理
     * 1.使用Enhancer类中的create方法
     * 创建代理对象的要求，被代理类不能是最终类
     *
     */
    public static void main(final String[] args) {
       final Producer producer = new Producer();

        Producer proxy = (Producer) Enhancer.create(Producer.class, new MethodInterceptor() {
            /**
             * 执行动态代理对象的任何方法都会经过该方法
             * @param o
             * @param method
             * @param args
             * @param methodProxy
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                float money= (float) args[0];
                //float v = Float.parseFloat(money);
                if ("seleProduct".equals(method.getName())) {
                    result = method.invoke(producer, money * 0.8f);
                }
                return result;
            }
        });
        proxy.seleProduct(10000);

    }

}
