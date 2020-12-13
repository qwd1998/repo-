package cn.itcast.service.impl;

import cn.itcast.dao.IAccountDao;
import cn.itcast.dao.impl.IAccountDaoImpl;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.Service;

public class ServiceImpl implements Service {

    private IAccountDao dao= (IAccountDao) BeanFactory.getBean("acc");
    @Override
    public void saveAccount() {
        dao.save();
        System.out.println(dao);
        System.out.println();
    }
}
