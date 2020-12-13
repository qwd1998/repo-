package cn.itcast.provider.controller;

import cn.itcast.provider.pojo.User;
import cn.itcast.provider.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService userService;

    @RequestMapping("{id}")
    @ResponseBody
    public User findById(@PathVariable("id")int id){
        return userService.findById(id);
    }
}
