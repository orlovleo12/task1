package main.java.dao;


import main.java.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        int result = (Integer) session.save(application);
    }

    @Override
    public void deleteUser(int userId) {
        session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, userId);
        session.delete(user);
    }

    @Override
    public void updateUser(User application) {
        session = sessionFactory.getCurrentSession();
        User user = application;
        session.saveOrUpdate(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> empList = null;
        session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        empList = criteria.list();
        return empList;
    }

    @Override
    public User getUserById(int userId) {
        session = sessionFactory.getCurrentSession();
        User result = (User) session.load(User.class, userId);
        return result;
    }

    public User getUserByLogin(String login) {
        session = sessionFactory.getCurrentSession();
        User result = (User) session.createCriteria(User.class, login)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
        return result;
    }
}
