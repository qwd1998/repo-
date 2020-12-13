package cn.itcast.servlet;

import cn.itcast.factory.BeanFactory;
import cn.itcast.service.Service;

public class servlet {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Service service = (Service) BeanFactory.getBean("accounts");
            System.out.println(service);
            service.saveAccount();

        }

    }
}
