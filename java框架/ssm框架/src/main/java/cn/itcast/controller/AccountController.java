package cn.itcast.controller;

import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.itcast.domain.Account;
import java.util.List;

@Controller
@RequestMapping(path = "/account")
public class AccountController {
    @Autowired
    private AccountService as;

    @RequestMapping("/findAll")
    public String test(Account account,Model model){
        as.saveAccount(account);
        List<Account> list = as.findAll();
        model.addAttribute("list",list);
        System.out.println("表现层：查询了所有用户信息");
        return "success";
    }
}
