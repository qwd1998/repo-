package cn.itcast.service;

import cn.itcast.domain.Account;

import java.util.List;

public interface AccountService {
    /**
     * 查询账户所有
     * @return
     */
    public List<Account> findAllAccount();

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    public Account findOneAccount(Integer id);

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

    /**
     * 查询记录数
     * @return
     */
    int CountAccount();

    public void trans(Integer aid,Integer bid,Integer money);


}
