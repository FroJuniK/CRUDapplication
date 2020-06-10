package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;
import java.util.List;

public class Service {
    private static Service service;
    private UserDAO dao;

    private Service() {
        dao = UserDaoFactory.getUserDAO();
    }

    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }
        return service;
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
