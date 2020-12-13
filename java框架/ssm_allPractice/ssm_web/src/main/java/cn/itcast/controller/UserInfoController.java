package cn.itcast.controller;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUser")
    public ModelAndView findAllUser(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList = userService.findAll(page, size);
        PageInfo userList = new PageInfo(userInfoList);
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String saveUser(UserInfo userInfo) {
        // ModelAndView mv = new ModelAndView();
        userService.save(userInfo);
        return "redirect:findAllUser";
    }

    @RequestMapping("/findById")
    public ModelAndView findByIdUser(String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) {
        // ModelAndView mv = new ModelAndView();
        userService.findUserByIdAndAllRole(userId, roleIds);
        return "redirect:findAllUser";
    }

    @RequestMapping("/findById2")
    public ModelAndView findById2User(@RequestParam(name = "id", required = true) String userId) {
        ModelAndView mv = new ModelAndView();
        UserInfo user2 = userService.findById(userId);

        List<Role> roles = userService.findNotRole(userId);
        mv.addObject("user2", user2);
        mv.addObject("roles", roles);
        mv.setViewName("userAndRole-add");
        return mv;
    }


    @RequestMapping("/delete")
    public String deleteUser(String id) {
        // ModelAndView mv = new ModelAndView();
        userService.delete(id);
        return "redirect:findAllUser";
    }


 /*
    @RequestMapping("/findOne2")
    public ModelAndView findOne2Product(String id) {
        ModelAndView mv = new ModelAndView();
        Product one = productService.findOne(id);
        mv.addObject("One",one);
        mv.setViewName("product-show");
        return mv;
    }

    @RequestMapping("/update")
    public String updateProduct(Product product) {
        productService.update(product);
        return "redirect:findAllProduct";
    }*/

}
