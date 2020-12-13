package cn.itcast.Service.impl;

import cn.itcast.Service.AccountService;
import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;
import cn.itcast.utils.TransactionManager;

import java.util.List;


public class AccountServiceImpl3 implements AccountService {

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
    public Account fineOne(String name) {
        return accountDao.findOne(name);
    }

    @Override
    public void transfer(String sourceName, String targetName, float money) {

            Account one = accountDao.findOne(sourceName);
            Account two = accountDao.findOne(targetName);

            one.setMoney(one.getMoney()-money);

            two.setMoney(two.getMoney()+money);
            accountDao.updateAccount(one);
            int i=1/0;
            accountDao.updateAccount(two);
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
