package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.sql.Connection;
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
                                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                                    "age TINYINT NOT NULL)")
                    .addEntity(User.class);
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {

        try(Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            Query query = session.createSQLQuery(
                            "DROP TABLE IF EXISTS users")
                    .addEntity(User.class);
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.getCurrentSession()){
            User user = new User(name, lastName, age);

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
