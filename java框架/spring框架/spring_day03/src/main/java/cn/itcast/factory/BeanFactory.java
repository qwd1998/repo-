package cn.itcast.factory;

import cn.itcast.Service.AccountService;
import cn.itcast.domain.Account;
import cn.itcast.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
    private AccountService accountService;

    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    private TransactionManager manager;

    public void setManager(TransactionManager manager) {
        this.manager = manager;
    }

    public AccountService getAccountService(){
        return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke=null;
                try{
                    //1.开启事务
                    manager.begin();
                    //2.执行操作
                     invoke = method.invoke(accountService, args);
                    //3.提交事务
                    manager.commit();
                    //4.返回结果
                    return invoke;
                } catch (Exception e) {
                    //5.回滚操作
                    manager.rollback();
                    throw new RuntimeException("数据错误，回滚事务");
                }finally {
                    //6.释放连接
                    manager.release();
                }

            }
        });
    }


}
