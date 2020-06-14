package service;

import model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);

    boolean addUser(User user);

    boolean deleteUser(long id);

    boolean editUser(User user);
}
