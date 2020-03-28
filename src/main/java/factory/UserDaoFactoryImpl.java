package main.java.factory;


import main.java.dao.UserDao;
import main.java.dao.UserDaoHibernateImpl;
import main.java.dao.UserDaoJDBCImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactoryImpl {
    public static UserDao getDao() {
        String dao = "";
        try {
            InputStream inputStream = UserDaoFactoryImpl.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();
            prop.load(inputStream);
            dao = prop.getProperty("dao");

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dao.equals("jdbc")) {
            return new UserDaoJDBCImpl(DBHelper.getInstance().getConnection());
        } else {
            return new UserDaoHibernateImpl(DBHelper.getInstance().getSessionFactory());
        }


    }
}
