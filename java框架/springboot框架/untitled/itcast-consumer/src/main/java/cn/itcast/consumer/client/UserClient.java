package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "service-provider",fallback = UserClientImpl.class)
public interface UserClient {

    @RequestMapping("user/{id}")
    @ResponseBody
    public User findById(@PathVariable("id")int id);
}
