package cn.binean.mvcproject.test;

import cn.binean.mvcproject.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUtilsTest {

    @Test
    void getConnection() {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
    }
}