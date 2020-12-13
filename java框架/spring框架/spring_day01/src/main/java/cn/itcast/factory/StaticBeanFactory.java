package cn.itcast.factory;

import cn.itcast.service.Service;
import cn.itcast.service.impl.ServiceImpl;

public class StaticBeanFactory {
    public static Service getService(){
        return new ServiceImpl();
    }
}
