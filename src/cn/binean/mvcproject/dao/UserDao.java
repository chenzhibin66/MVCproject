package cn.binean.mvcproject.dao;

import cn.binean.mvcproject.model.User;

import java.sql.Connection;
import java.util.List;

/**
 * 接口制定规则，只制定方法，但不去实现，UserDao：定义与Users数据表有关系的操作方法
 */

public interface UserDao {
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
    public User get(Connection conn,int id);

    /**
     * 获取所有用户数据
     * @return
     */
    public List<User> getListAll();

    public long getCountByName(String username);

    /**
     * Dao层实现模糊查询的方法
     * @param username
     * @param address
     * @param phoneNo
     * @return
     */
    public List<User> query(String username, String address, String phoneNo);
}
