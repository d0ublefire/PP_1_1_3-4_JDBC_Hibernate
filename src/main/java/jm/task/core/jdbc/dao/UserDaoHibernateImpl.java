package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users ("
            + " id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
            + "name VARCHAR(30) NOT NULL,"
            + "lastName VARCHAR(30) NOT NULL,"
            + "age INT)";
    private final String DROP_TABLE = "DROP TABLE IF EXISTS users";
    private final String CLEAN_TABLE = "TRUNCATE TABLE users";

    private final String GET_ALL_USERS = "SELECT * FROM users";


    private final Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(CREATE_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(DROP_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            session.beginTransaction();
            users = session.createNativeQuery(GET_ALL_USERS, User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(CLEAN_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }
}