package cn.itcast.service.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findOneAccount(Integer id) {
        return accountDao.findOneAccount(id);
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

    @Override
    public int CountAccount() {
        return accountDao.CountAccount();
    }

    @Override
    public void trans(Integer aid, Integer bid, Integer money) {
        Account one = accountDao.findOneAccount(aid);
        Account two = accountDao.findOneAccount(bid);

        one.setMoney(one.getMoney()-money);

        two.setMoney(two.getMoney()+money);
        accountDao.updateAccount(one);
        //int i=1/0;
        accountDao.updateAccount(two);


    }


}
