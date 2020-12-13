package cn.itcast.Servlet;

import cn.itcast.Service.AccountService;

import cn.itcast.domain.Account;
import config.SpringConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServlet {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = (AccountService) ac.getBean("AccountService");
        as.transfer("aaa","bbb",-100);

    }
}
