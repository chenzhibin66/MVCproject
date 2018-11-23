package cn.binean.mvcproject.test;

import cn.binean.mvcproject.dao.UserDao;
import cn.binean.mvcproject.dao.UserDaoImpl;
import cn.binean.mvcproject.model.User;
import cn.binean.mvcproject.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    void get() {
        Connection conn = JdbcUtils.getConnection();
        User user = null;
        try {
            conn.setAutoCommit(false);
            user = userDao.get(conn, 3);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtils.closeConn(conn);
        }
        System.out.println(user);
    }
}