package cn.itcast.controller;

import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findOneUser")
    public ModelAndView findOneUser(){
        ModelAndView mv = new ModelAndView();
        User oneUser = userService.findOneUser(41);
        System.out.println(oneUser);
        mv.addObject("user",oneUser);
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping("/findAllUser")
    public ModelAndView findAllUser(){
        ModelAndView mv = new ModelAndView();
        List<User> users = userService.findAllUser();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getRoles());
            System.out.println(user.getAccounts());
        }

        mv.addObject("users",users);
        mv.setViewName("success");
        return mv;
    }
}
