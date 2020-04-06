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
        List<User> empList = null;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        empList = criteria.list();
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
        User result = (User) session.createCriteria(User.class, login)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
        session.getTransaction().commit();
        return result;
    }
}
