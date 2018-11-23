package cn.binean.mvcproject.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    //数据库连接池c3p0
    private static DataSource dataSource = null;

    static {   //静态的代码块只会被执行一次
        dataSource = new ComboPooledDataSource("mysql");
    }

    /**
     * jdbc工具类
     *
     * @return
     */
    public static Connection getConnection() {
        /**
         * 获取到数据库MySQL的数据连接对象conn
         */
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(Connection conn){
        /**
         * 通用的关闭数据库连接对象的方法
         */
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollbackTransation(Connection conn){
        if (conn!=null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
