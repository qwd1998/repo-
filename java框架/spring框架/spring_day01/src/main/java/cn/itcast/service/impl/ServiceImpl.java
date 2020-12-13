package cn.itcast.service.impl;

import cn.itcast.dao.IAccountDao;
import cn.itcast.dao.impl.IAccountDaoImpl;

import cn.itcast.service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceImpl implements Service {
    public ServiceImpl(){
        System.out.println("ServiceImpl的默认构造方法实现了");
    }

    @Override
    public void saveAccount() {

    }
}
