package main.java.dao;


import main.java.model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;
    private Session session;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User application) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        int result = (Integer) session.save(application);
        session.getTransaction().commit();
    }

    @Override
    public void deleteUser(int userId) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, userId);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public void updateUser(User application) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = application;
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> empList;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        empList = (List<User>) session.createQuery("from User").list();
        session.getTransaction().commit();
        return empList;
    }

    @Override
    public User getUserById(int userId) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User result = (User) session.load(User.class, userId);
        session.getTransaction().commit();
        return result;
    }

    public User getUserByLogin(String login) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query =session.createQuery("from User where login=:login");
        User result = (User) query.setParameter("login", login).uniqueResult();
        session.getTransaction().commit();
        return result;
    }
}
