package cn.itcast.Servlet;

import cn.itcast.Service.AccountService;
import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServlet {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean2.xml");
        AccountService as = (AccountService) ac.getBean("AccountService");
        /*IAccountDao adao = (IAccountDao) ac.getBean("IAccount");
        adao.save();*/

        List<Account> lists = as.findAll();
        for (Account list : lists) {
            System.out.println(list);
        }
    }
}
