package cn.itcast.service.impl;


import cn.itcast.dao.IAccountDao;
import cn.itcast.service.Service;

import java.util.Date;

public class ServiceImpl2 implements Service {
    private String name;
    private Integer age;
    private Date birthday;
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public void saveAccount() {
        System.out.println(name+"---"+age+"---"+birthday);
        accountDao.save();
    }
}
