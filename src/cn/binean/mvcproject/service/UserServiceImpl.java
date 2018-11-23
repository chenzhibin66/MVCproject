package cn.binean.mvcproject.service;

import cn.binean.mvcproject.dao.FactoryDao;
import cn.binean.mvcproject.dao.UserDao;
import cn.binean.mvcproject.dao.UserDaoImpl;
import cn.binean.mvcproject.model.User;
import cn.binean.mvcproject.utils.JdbcUtils;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = FactoryDao.getUserDao();

    @Override
    public int save(User user) {
        return userDao.save(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public User getTransation(int id) {
        Connection conn = null;
        User user = null;
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false); //开始事务
            user = userDao.get(conn, id);
            conn.commit();
        } catch (Exception e) {
            JdbcUtils.rollbackTransation(conn);
        }
        return user;
    }

    @Override
    public List<User> getListAll() {
        return userDao.getListAll();
    }

    @Override
    public long getCountByName(String username) {
        return userDao.getCountByName(username);
    }

    @Override
    public List<User> query(String username, String address, String phoneNo) {
        return userDao.query(username,address,phoneNo);
    }
}
