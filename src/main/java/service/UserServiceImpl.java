package main.java.service;


import main.java.dao.UserDao;
import main.java.factory.UserDaoFactoryImpl;
import main.java.model.User;

import java.util.List;

public class UserServiceImpl implements UserServiсe {
    private UserDao userDao = UserDaoFactoryImpl.getDao();
    private static UserServiceImpl userServiceImpl = new UserServiceImpl();
    public UserServiceImpl() {

    }
    public static UserServiceImpl getInstance() {
        return userServiceImpl;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
