package cn.itcast.service;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserInfo findById(String id);


    List<UserInfo> findAll(int page, int size);

    void save(UserInfo userInfo);

    void findUserByIdAndAllRole(String userId,String[] roleIds);

/*   UserInfo findById2(String userId);*/

    void delete(String id);

    List<Role> findNotRole(String userId);
}
