package cn.itcast.service;

import cn.itcast.mapper.UserMapper;
import cn.itcast.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

     public User queryUserById(int id){

         return this.userMapper.selectByPrimaryKey(id);
     }

     @Transactional
     public void deleteUserById(int id){
         userMapper.deleteByPrimaryKey(id);
     }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
