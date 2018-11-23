package cn.binean.mvcproject.dao;

import cn.binean.mvcproject.model.User;
import cn.binean.mvcproject.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个在dao层里的基本类，在于被具体的dao类，UserDao去继承它来用,不能new BaseDao（）来直接用
 *
 * @param <T> ：针对将要操作的各张数据表映射到java工程里的java类，User
 */
public class BaseDao<T> {

    QueryRunner queryRunner = new QueryRunner();

    private Class<T> aClass;

    public BaseDao() {
        //用Basedao的构造方法初始化aclass的属性,User   User.class
        Type superType = this.getClass().getGenericSuperclass();  //getGenericSuperclass的作用是拿到调用者的父类的类型
        if (superType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) superType;
            Type[] tarry = pt.getActualTypeArguments();//返回一个类型数组，第一个元素就是我们要的泛型T，User.class
            if (tarry[0] instanceof Class) {
                aClass = (Class<T>) tarry[0];
            }
        }
    }

    /**
     * 查询数据表，取出sql语句的结果集的第一条数据，封装成一个类的对象返回，不支持事务
     * 用到dbUtils工具类
     * BeanHandler括号里应该传入BaseDao<T>里边的T的真正用的时候的类型的Class
     *
     * @param sql
     * @param args
     * @return
     */
    public T get(String sql, Object... args) {
        Connection conn = null;
        T entity = null;
        try {
            //拿conn
            conn = JdbcUtils.getConnection();
            entity = queryRunner.query(conn, sql, new BeanHandler<>(aClass), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConn(conn);
        }
        return entity;
    }

    /**
     * 查询数据表，取出sql语句的结果集的第一条数据，封装成一个类的对象返回，支持事务
     *
     * @param sql
     * @param args
     * @return
     */
    public T get(Connection conn, String sql, Object... args) {
        T entity = null;
        try {
            entity = queryRunner.query(conn, sql, new BeanHandler<>(aClass), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 获取多条记录的通用方法
     *
     * @return
     */
    public List<T> getList(String sql, Object... args) {
        Connection conn = null;
        List<T> list = null;
        try {
            //拿conn
            conn = JdbcUtils.getConnection();
            list = queryRunner.query(conn, sql, new BeanListHandler<>(aClass), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConn(conn);
        }
        return list;
    }

    /**
     * 通用于实现insert，update，delete通用的更新方法
     *
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object... args) {
        Connection conn = null;
        int rows = 0;
        try {
            //拿conn
            conn = JdbcUtils.getConnection();
            rows = queryRunner.update(conn, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConn(conn);
        }
        return rows;

    }

    /**
     * 通用的返回sql语句的结果只有一个数值的类型的查询，用户个数，count（id）
     *
     * @return
     */
    public Object getValue(String sql, Object... args) {
        Connection conn = null;
        Object obj = null;
        try {
            //拿conn
            conn = JdbcUtils.getConnection();
            obj = queryRunner.query(conn, sql, new ScalarHandler<>(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConn(conn);
        }
        return obj;

    }
}
