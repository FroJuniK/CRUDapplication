package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDAO dao = new UserDAO();

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        try {
            return dao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(long id) {
        try {
            return dao.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user) {
        try {
            return dao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(long id) {
        try {
            return dao.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editUser(User user) {
        try {
            return dao.editUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createTable() {
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            dao.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
