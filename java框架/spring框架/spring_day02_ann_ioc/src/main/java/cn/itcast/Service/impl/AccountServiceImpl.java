/*
package cn.itcast.Service.impl;

import cn.itcast.Service.AccountService;
import cn.itcast.dao.IAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Date;

@Service(value = "AccountService")
public class AccountServiceImpl {

    @Autowired
    @Qualifier("IAccount")

    private IAccountDao accountDao;

    private int id;
    private String name;
    private Date birthday;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public void saveAccount() {
        System.out.println("我输出了.......");
        System.out.println("id:" + id + "--姓名:" + name + "--生日:" + birthday);
      //  accountDao.save();

    }
}
*/
