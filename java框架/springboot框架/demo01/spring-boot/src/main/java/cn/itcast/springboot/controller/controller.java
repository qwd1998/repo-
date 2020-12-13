package cn.itcast.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController  //RestController返回的是json数据
@RequestMapping("/hello")
public class controller {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/show")
    public String test() {
        return "hello spring boot 我的";
    }

}
