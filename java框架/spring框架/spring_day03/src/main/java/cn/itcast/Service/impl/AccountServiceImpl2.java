package cn.itcast.Service.impl;

import cn.itcast.Service.AccountService;
import cn.itcast.dao.IAccountDao;
import cn.itcast.domain.Account;
import cn.itcast.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class AccountServiceImpl2 implements AccountService {

    private TransactionManager manager;

    public void setManager(TransactionManager manager) {
        this.manager = manager;
    }

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        try{
            //1.开启事务
            manager.begin();
            //2.执行操作
            List<Account> accounts = accountDao.findAll();
            //3.提交事务
            manager.commit();
            //4.返回结果
            return  accounts;
        } catch (Exception e) {
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException("数据错误，回滚事务");
        }finally {
            //6.释放连接
            manager.release();
        }
    }

    @Override
    public Account fineOne(int id) {
        try{
            //1.开启事务
            manager.begin();
            //2.执行操作
            Account accounts = accountDao.fineOne(id);
            //3.提交事务
            manager.commit();
            //4.返回结果
            return  accounts;
        } catch (Exception e) {
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException("数据错误，回滚事务");
        }finally {
            //6.释放连接
            manager.release();
        }
    }

    @Override
    public Account fineOne(String name) {
        try{
            //1.开启事务
            manager.begin();
            //2.执行操作
            Account accounts = accountDao.findOne(name);
            //3.提交事务
            manager.commit();
            //4.返回结果
            return  accounts;
        } catch (Exception e) {
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException("数据错误，回滚事务");
        }finally {
            //6.释放连接
            manager.release();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, float money) {
        try{
            //1.开启事务
            manager.begin();
            //2.执行操作
            Account one = accountDao.findOne(sourceName);
            Account two = accountDao.findOne(targetName);

            one.setMoney(one.getMoney()-500);

            two.setMoney(two.getMoney()+500);
            accountDao.updateAccount(one);
           // int i=1/0;
            accountDao.updateAccount(two);
            //3.提交事务
            manager.commit();
            //4.返回结果

        } catch (Exception e) {
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException("数据错误，回滚事务");
        }finally {
            //6.释放连接
            manager.release();
        }

    }

    @Override
    public void updateAccount(Account account) {
        try{
            //1.开启事务
            manager.begin();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            manager.commit();
            //4.返回结果

        } catch (Exception e) {
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException("数据错误，回滚事务");
        }finally {
            //6.释放连接
            manager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try{
            //1.开启事务
            manager.begin();
            //2.执行操作
            accountDao.saveAccount(account);
            //3.提交事务
            manager.commit();
            //4.返回结果

        } catch (Exception e) {
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException("数据错误，回滚事务");
        }finally {
            //6.释放连接
            manager.release();
        }
    }

    @Override
    public void deleteAccount(int id) {
        try{
            //1.开启事务
            manager.begin();
            //2.执行操作
             accountDao.deleteAccount(id);
            //3.提交事务
            manager.commit();
            //4.返回结果

        } catch (Exception e) {
            //5.回滚操作
            manager.rollback();
            throw new RuntimeException("数据错误，回滚事务");
        }finally {
            //6.释放连接
            manager.release();
        }
    }

}
