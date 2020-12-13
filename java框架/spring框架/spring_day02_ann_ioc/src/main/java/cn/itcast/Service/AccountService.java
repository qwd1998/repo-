package cn.itcast.Service;

import cn.itcast.domain.Account;

import java.util.List;

public interface AccountService {
    //public void saveAccount();

    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 查询一个
     * @param id
     * @return
     */
    Account fineOne(int id);

    /**
     * 修改信息
     * @param account
     * @return
     */
    void updateAccount(Account account);

    /**
     * 保存用户
     */
    void saveAccount(Account account);

    /**
     * 删除用户
     * @param id
     */
    void deleteAccount(int id);

}
