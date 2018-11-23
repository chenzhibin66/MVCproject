package cn.binean.mvcproject.dao;

import cn.binean.mvcproject.model.User;

import java.sql.Connection;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public int save(User user) {
        String sql = "insert into user(username,pasword,phonenum,address,reg_date) values(?,?,?,?,?)";
        return super.update(sql, user.getUsername(), user.getPasword(), user.getPhoneNo(), user.getAddress(), user.getRegDate());

    }

    @Override
    public int deleteUserById(int id) {
        String sql = "delete from user where id =?";
        return super.update(sql, id);
    }

    @Override
    public int updateUserById(User user) {
        String sql = "update user set username=?,pasword=?,phonenum=?,address=? where id =?";
        return super.update(sql, user.getUsername(), user.getPasword(), user.getPhoneNo(), user.getAddress(), user.getId());

    }

    /**
     * 不支持事务
     *
     * @param id
     * @return
     */
    @Override
    public User get(int id) {
        String sql = "select id,username,pasword,phonenum phoneNo,address,reg_date regDate from user where id=?; ";
        return super.get(sql, id);
    }

    public User get(Connection conn, int id) {
        String sql = "select id,username,pasword,phonenum phoneNo,address,reg_date regDate from user where id=?; ";
        return super.get(conn, sql, id);
    }

    @Override
    public List<User> getListAll() {
        String sql = "select id,username,password,phonenum phoneNo,address,reg_date regDate from user;";
        return super.getList(sql);
    }

    @Override
    public long getCountByName(String username) {
        String sql = "select COUNT(id) from user where username = ?;";
        return (long) super.getValue(sql, username);
    }

    @Override
    public List<User> query(String username, String address, String phoneNo) {
        String sql = "select id,username,pasword,phonenum phoneNo,address,reg_date regDate from user where 1=1";
        if (username != null && !"".equals(username)) {
            sql = sql + " AND username like '%" + username + "%'";   //sql注入攻击的风险
        }
        if (address != null && !"".equals(address)) {
            sql = sql + " AND address like '%" + address + "%'";
        }
        if (phoneNo != null && !"".equals(phoneNo)) {
            sql = sql + " AND phonenum like '%" + phoneNo + "%'";
        }
        System.out.println(sql);
        return super.getList(sql);
    }
}
