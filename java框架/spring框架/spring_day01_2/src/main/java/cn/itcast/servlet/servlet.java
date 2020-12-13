package cn.itcast.servlet;


import cn.itcast.service.Service;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class servlet {

    /**
     * 获取spring的Ioc核心容器，并根据id获取对象
     *
     * 核心容器两个接口的问题
     *      ApplicationContext：在构建核心容器时，创建对象采取的策略是立即加载          适合单例对象  开发中最为常用
     *                          一读取完xml配置文件就立即创建对象
     *
     *      BeanFactory：在构建核心容器时，创建对象采取的策略是延迟加载                 适合多例对象
     *                   什么时候用到，什么时候创建对象
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取Bean对象
       // Service  as = (Service) ac.getBean("Service");
        //Service as1 = ac.getBean("accountService", Service.class);

        Service as = (Service) ac.getBean("Service2");
       // as.saveAccount();

       /* Service as = (Service) ac.getBean("Service3");*/
        as.saveAccount();







    }
}
