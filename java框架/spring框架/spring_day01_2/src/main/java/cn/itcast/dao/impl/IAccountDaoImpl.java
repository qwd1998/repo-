package cn.itcast.dao.impl;

import cn.itcast.dao.IAccountDao;
import org.springframework.stereotype.Repository;

@Repository(value="IAccount")
public class IAccountDaoImpl implements IAccountDao {
    @Override
    public void save() {
        System.out.println("保存了用户");
    }
}
