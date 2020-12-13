package cn.itcast.service.controller;

import cn.itcast.service.feign.UserFeign;
import cn.itcast.service.pojo.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("consumer/user")
// 使用feign管理 不需要@DefaultProperties(defaultFallback = "fallBack")
public class userController {

   /* @Autowired
    private RestTemplate restTemplate;
    使用feign管理
    */

    //@Autowired
    //private DiscoveryClient discoveryClient;  //包含了拉去的所有服务信息

    @Autowired
    private UserFeign userFeign;

    @RequestMapping
    @ResponseBody
    //@HystrixCommand
    public String findById(@RequestParam(name = "id")int id){
        /*List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance instance = instances.get(0);*/
        //return restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id,User.class);
        //return restTemplate.getForObject("http://service-provider/user/"+id,String.class);
        return userFeign.findById(id).toString();
    }
/*
    public String fallBack(){

        return "服务器正忙，请稍后再试！！";
    }*/

}
