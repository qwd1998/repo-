package cn.itcast.Service.impl;

import cn.itcast.Service.AccountService;
import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value = "AccountService")
public class AccountServiceImpl2 implements AccountService {
    /*@Autowired
    @Qualifier(value = "IAccount")*/
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account fineOne(int id) {
        return accountDao.fineOne(id);
    }

    @Override
    public void updateAccount(Account account) {
            accountDao.updateAccount(account);
    }

    @Override
    public void saveAccount(Account account) {
            accountDao.saveAccount(account);
    }

    @Override
    public void deleteAccount(int id) {
            accountDao.deleteAccount(id);
    }

}
