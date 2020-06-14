package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl service;
    private UserDAO dao;

    private UserServiceImpl() {
        dao = UserDaoFactory.getUserDAO();
    }

    public static UserServiceImpl getInstance() {
        if (service == null) {
            service = new UserServiceImpl();
        }
        return service;
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return dao.getUserById(id);
    }

    @Override
    public boolean addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    public boolean deleteUser(long id) {
        return dao.deleteUser(id);
    }

    @Override
    public boolean editUser(User user) {
        return dao.editUser(user);
    }
}
