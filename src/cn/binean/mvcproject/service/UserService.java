package cn.binean.mvcproject.service;

import cn.binean.mvcproject.model.User;

import java.sql.Connection;
import java.util.List;

public interface UserService {
    /**
     * 实现插入一条新的用户信息数据
     * @param user
     * @return
     */
    public int save(User user);

    /**
     * 根据用户编号删除对应的用户数据
     * @param id
     * @return
     */
    public int deleteUserById(int id);

    /**
     * 根据用户id去修改对应的用户信息数据
     * @param id
     * @return
     */
    public int updateUserById(User user);

    /**
     * 根据用户编号来获取一条用户数据，封装成User的一个对象
     * @param id
     * @return
     */
    public User get(int id);

    /**
     * 支持事务
     * @param conn
     * @param id
     * @return
     */
    public User getTransation(int id);

    /**
     * 获取所有用户数据
     * @return
     */
    public List<User> getListAll();

    public long getCountByName(String username);

    /**
     * 用户模糊查询的方法
     * @param username
     * @param address
     * @param phoneNo
     * @return
     */
    public List<User> query(String username, String address, String phoneNo);
}
