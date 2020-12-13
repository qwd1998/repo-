package cn.itcast.service.feign;

import cn.itcast.service.feign.impl.UserFeignImpl;
import cn.itcast.service.pojo.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "service-provider",fallback = UserFeignImpl.class)
public interface UserFeign {

    @RequestMapping("user/{id}")
    @ResponseBody
    public User findById(@PathVariable("id")int id);
}
