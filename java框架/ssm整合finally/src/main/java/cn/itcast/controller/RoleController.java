package cn.itcast.controller;

import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findOneRole")
    public ModelAndView findOneRole(){
        ModelAndView mv = new ModelAndView();
        Role oneRole = roleService.findOneRole(1);
        System.out.println(oneRole);
        mv.addObject("role",oneRole);
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping("/findAllRole")
    public ModelAndView findAllRole(){
        ModelAndView mv = new ModelAndView();
        List<Role> list = roleService.findAllRole();
        for (Role role : list) {
            System.out.println(role);
            System.out.println(role.getUsers());
        }
        mv.addObject("roles",list);


        mv.setViewName("success");
        return mv;
    }
}
