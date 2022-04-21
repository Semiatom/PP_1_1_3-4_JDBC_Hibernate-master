package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = new Util().getSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            Query query = session.createSQLQuery(
                            "CREATE TABLE IF NOT EXISTS users " +
                                    "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                                    "age TINYINT NOT NULL)");
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {

        try(Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            Query query = session.createSQLQuery(
                    "DROP TABLE IF EXISTS users");
                    query.executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User(name, lastName, age);

        try (Session session = sessionFactory.getCurrentSession()){

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.getCurrentSession()){

            session.beginTransaction();
            session.createQuery("delete User where id = id").executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public List<User> getAllUsers() {

        List <User> users;

        try (Session session = sessionFactory.getCurrentSession()){

            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()){

            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        }

    }
}
