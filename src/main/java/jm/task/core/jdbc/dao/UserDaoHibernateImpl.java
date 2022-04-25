package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = new Util().getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS users " +
                            "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                            "age TINYINT NOT NULL)");
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(
                    "DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User(name, lastName, age);

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }

    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createQuery("delete User where id = id").executeUpdate();
            transaction.commit();
        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            transaction.commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        }

    }
}
