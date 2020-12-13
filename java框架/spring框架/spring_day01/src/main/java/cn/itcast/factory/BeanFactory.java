package cn.itcast.factory;

import cn.itcast.service.Service;
import cn.itcast.service.impl.ServiceImpl;

public class BeanFactory {
    public Service getService(){
        return new ServiceImpl();
    }
}
