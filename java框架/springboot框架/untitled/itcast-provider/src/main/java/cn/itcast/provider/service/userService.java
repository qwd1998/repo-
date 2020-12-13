package cn.itcast.provider.service;

import cn.itcast.provider.mapper.UserMapper;
import cn.itcast.provider.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private UserMapper userMapper;

    public User findById(int id){

        return userMapper.selectByPrimaryKey(id);
    }
}
