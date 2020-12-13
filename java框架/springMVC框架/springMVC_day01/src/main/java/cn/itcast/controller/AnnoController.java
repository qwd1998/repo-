package cn.itcast.controller;

import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Map;

/**
 * 常用的注解
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value={"msg"}) //把msg=德玛西亚的力量 从request域中也存进session域中
public class AnnoController {

    @RequestMapping(path = "/testRequestParam")
    /**
     * 加入了@RequestParam参数必须为name="name"的name,jsp中的参数不写都不行
     */
    public String testRequestParam(@RequestParam(name="name" ) String username){
        System.out.println("执行了呀....");
        System.out.println(username);

        return "success";
    }

    @RequestMapping(path = "/testRequestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println("执行了呀....");
        System.out.println(body);

        return "success";
    }

    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable(name = "id") String id){
        System.out.println("执行了呀....");
        System.out.println(id);

        return "success";
    }

    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(name = "accept") String header){
        System.out.println("执行了呀....");
        System.out.println(header);

        return "success";
    }

    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue){
        System.out.println("执行了呀....");
        System.out.println(cookieValue);

        return "success";
    }

    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute(name="aa") User user){
        System.out.println("执行了呀....");
        System.out.println(user);

        return "success";
    }

    /**
     * @ModelAttribute这个加上之后，优先其他方法执行,可以作用在方法上，也可以作用在参数上
     * @param name
     * @return
     */
    /*@ModelAttribute
    public User showUser(String name){
        System.out.println("ModelAttribute注解执行了....");
        User user = new User();
        user.setName("张三");
        user.setSex("女");
        user.setBirthday(new Date());
        return user;
    }*/

    @ModelAttribute
    public void showUser(String name, Map<String,User> map){
        System.out.println("ModelAttribute注解执行了....");
        User user = new User();
        user.setName("张三");
        user.setSex("女");
        user.setBirthday(new Date());
        map.put("aa",user);
    }

    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(User user,Model model){
        System.out.println("执行了呀....");
        model.addAttribute("msg",user);
        return "success";
    }

    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap){
        System.out.println("getSessionAttributes执行了呀....");
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }

    @RequestMapping("/deleteSessionAttributes")
    public String deleteSessionAttributes(SessionStatus status){
        System.out.println("deleteSessionAttributes执行了呀....");
        status.setComplete();
        return "success";
    }
}
