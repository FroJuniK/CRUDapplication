package service;

import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;

import java.util.List;

public class Service {
    private static Service userService;
//    private UserJdbcDAO dao = new UserJdbcDAO();
    private UserHibernateDAO dao = new UserHibernateDAO();

    private Service() {
    }

    public static Service getInstance() {
        if (userService == null) {
            userService = new Service();
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
