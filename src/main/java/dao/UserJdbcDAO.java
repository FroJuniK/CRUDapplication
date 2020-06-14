package dao;

import model.User;
import util.DBHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO() {
        this.connection = DBHelper.getInstance().getConnection();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT * FROM users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String dateOfBirth = result.getString("dateOfBirth");
                list.add(new User(id, name, email, dateOfBirth));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try (PreparedStatement prStmt = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
            prStmt.setLong(1, id);
            prStmt.execute();
            ResultSet result = prStmt.getResultSet();
            if (result.next()) {
                String name = result.getString("name");
                String email = result.getString("email");
                String dateOfBirth = result.getString("dateOfBirth");
                user = new User(id, name, email, dateOfBirth);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        boolean isAdded = false;
        try (PreparedStatement prStmt = connection.prepareStatement(
                "INSERT INTO users (name, email, dateOfBirth) VALUES (?, ?, ?)")) {
            prStmt.setString(1, user.getName());
            prStmt.setString(2, user.getEmail());
            prStmt.setString(3, user.getDateOfBirth());
            isAdded = prStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public boolean deleteUser(long id) {
        int result = 0;
        try (PreparedStatement prStmt = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            prStmt.setLong(1, id);
            result = prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean editUser(User user) {
        int result = 0;
        try (PreparedStatement prStmt = connection.prepareStatement(
                "UPDATE users SET name=?, email=?, dateOfBirth=? WHERE id=?")) {
            prStmt.setString(1, user.getName());
            prStmt.setString(2, user.getEmail());
            prStmt.setString(3, user.getDateOfBirth());
            prStmt.setLong(4, user.getId());
            result = prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }
}