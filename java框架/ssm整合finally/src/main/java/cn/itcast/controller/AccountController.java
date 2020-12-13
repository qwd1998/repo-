package cn.itcast.controller;

import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService as;

    @RequestMapping("/findAllAccount")
    public ModelAndView findAllACCount(){
        ModelAndView mv = new ModelAndView();
        List<Account> list = as.findAllAccount();
        mv.addObject("accounts",list);
        mv.setViewName("success");
        return mv;
    }
}
