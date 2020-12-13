package cn.itcast.consumer.controller;


import cn.itcast.consumer.client.UserClient;
import cn.itcast.consumer.pojo.User;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("consumer/user")
public class UserController {

    @Autowired
    private UserClient feignUserClient;

    @RequestMapping
    @ResponseBody
    public User findById(@RequestParam(name = "id") int id){

        return feignUserClient.findById(id);
    }
}
