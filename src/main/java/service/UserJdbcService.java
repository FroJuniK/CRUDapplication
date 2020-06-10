package service;

import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;
import util.DBHelper;

import java.util.List;

public class UserService {
    private static UserService userService;
//    private UserJdbcDAO dao = new UserJdbcDAO(DBHelper.getConnection());
    private UserHibernateDAO dao = new UserHibernateDAO(DBHelper.getSessionFactory().openSession());

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public User getUserById(long id) {
        return dao.getUserById(id);
    }

    public boolean addUser(User user) {
        return dao.addUser(user);
    }

    public boolean deleteUser(long id) {
        return dao.deleteUser(id);
    }

    public boolean editUser(User user) {
        return dao.editUser(user);
    }
}
