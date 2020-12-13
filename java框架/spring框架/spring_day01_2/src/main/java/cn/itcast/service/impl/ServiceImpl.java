package cn.itcast.service.impl;



import cn.itcast.dao.IAccountDao;
import cn.itcast.dao.impl.IAccountDaoImpl;
import cn.itcast.service.Service;
import java.util.Date;

public class ServiceImpl implements Service {
    private String name;
    private Integer age;
    private Date birthday;

    public ServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
    private IAccountDao accountDao;



    @Override
    public void saveAccount() {
        System.out.println(name+"---"+age+"---"+birthday);
        accountDao.save();
    }
}
