package cn.itcast.controller;

import cn.itcast.domain.Account;
import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 请求参数绑定
 */
@Controller
@RequestMapping("/param")
public class Param {

    @RequestMapping("test")
    public  String testParam(String username){
        System.out.println("执行了param");
        System.out.println(username);
        return "success";
    }

    @RequestMapping("saveAccount")
    public  String saveAccount(Account account){
        System.out.println("执行了param");
        System.out.println(account);
        return "success";
    }

    @RequestMapping("saveUser")
    public  String saveUser(User user){
        System.out.println("执行了param");
        System.out.println(user);
        return "success";
    }
}
