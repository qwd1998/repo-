package cn.itcast.Service.impl;

import cn.itcast.Service.IAccountService;
import org.springframework.stereotype.Service;

@Service
public class IAccountServiceImpl implements IAccountService {
    public void save() {
        System.out.println("执行了保存");
    }

    public void update(int id) {
        System.out.println("执行了更新");
    }

    public int delete() {
        System.out.println("执行了删除");
        return 0;
    }
}
