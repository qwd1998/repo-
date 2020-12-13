package cn.itcast.service.feign.impl;

import cn.itcast.service.feign.UserFeign;
import cn.itcast.service.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignImpl implements UserFeign {
    @Override
    public User findById(int id) {
        User user = new User();
        user.setUsername("服务器无响应，请等待");
        return user;
    }
}
