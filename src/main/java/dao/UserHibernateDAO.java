package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private SessionFactory factory;
    Transaction transaction = null;

    public UserHibernateDAO() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();

        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.openSession()) {
            List<User> list = session.createQuery("FROM model.User").list();
            return list;
        }
    }

    @Override
    public User getUserById(long id) {
        try (Session session = factory.openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    @Override
    public boolean addUser(User user) {
        long id = 0;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            id = (long) session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return id > 0;
    }

    @Override
    public boolean deleteUser(long id) {
        boolean isDeleted;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            isDeleted = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            isDeleted = false;
        }
        return isDeleted;
    }

    @Override
    public boolean editUser(User user) {
        boolean isEdited;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            isEdited = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            isEdited = false;
        }
        return isEdited;
    }
}