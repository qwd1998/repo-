package cn.itcast.Servlet;

import cn.itcast.Service.AccountService;

import cn.itcast.domain.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServlet {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = (AccountService) ac.getBean("AccountService");
        /*IAccountDao adao = (IAccountDao) ac.getBean("IAccount");
        adao.save();*/

        /*List<Account> lists = as.findAll();
        for (Account list : lists) {
            System.out.println(list);
        }*/
       /* new Thread() {
            @Override
            public void run() {
                Account a = as.fineOne("aaa");
                Account b = as.fineOne("bbb");

                a.setMoney(a.getMoney()-500);

                b.setMoney(b.getMoney()+500);
                as.updateAccount(a);
                int i=1/0;
                as.updateAccount(b);
            }
        }.start();*/
        as.transfer("aaa","bbb",500);
    }
}
