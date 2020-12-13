package cn.itcast.controller;

import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "wd你们打";
    }


    @GetMapping("/select")
    @ResponseBody
    public User queryUserById(@RequestParam(name = "id") int id){
        System.out.println(userService.queryUserById(id));
        return userService.queryUserById(id);
    }

    @GetMapping("/all")
    public String selectAll(Model model){
        List<User> users = userService.selectAll();
        model.addAttribute("users",users);
        return "users";
    }
}
