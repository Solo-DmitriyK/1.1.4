package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS tablename(id BIGINT NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, " +
                "lastName varchar(255) NOT NULL, age TINYINT, PRIMARY KEY(id))").executeUpdate();
        transaction.commit();
        session.getSessionFactory().close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS tablename").executeUpdate();
        transaction.commit();
        session.getSessionFactory().close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.getSessionFactory().close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.getSessionFactory().close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createCriteria(User.class).list();
        transaction.commit();
        session.getSessionFactory().close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
//        session.createQuery("delete from user").executeUpdate();
        List<User> list = session.createCriteria(User.class).list();
        for (User obj : list) {
            session.delete(obj);
        }
        transaction.commit();
        session.getSessionFactory().close();
    }
}
