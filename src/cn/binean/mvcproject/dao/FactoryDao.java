package cn.binean.mvcproject.dao;

public class FactoryDao {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
