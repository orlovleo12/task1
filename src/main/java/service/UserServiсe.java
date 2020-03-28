package main.java.service;


import main.java.model.User;

import java.util.List;

public interface UserServiсe {
    void addUser(User user);

    void deleteUser(int userId);

    void updateUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);

    User getUserByLogin(String login);

}
