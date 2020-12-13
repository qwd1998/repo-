package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {

    @Override
    public User findById(int id) {
        User user = new User();
        user.setUsername("熔断了，无法访问，请重试或通知管理员");
        return user;
    }
}
