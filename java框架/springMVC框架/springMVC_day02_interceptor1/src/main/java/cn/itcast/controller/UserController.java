package cn.itcast.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/textInterceptor")
    public String textInterceptor() {
        System.out.println("textInterceptor执行了......");

        return "success";
    }
}
