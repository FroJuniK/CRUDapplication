package dao;

import model.User;
import util.DBHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DBHelper.getConnection();
    }

    public List<User> getAllUsers() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM users");
        ResultSet result = stmt.getResultSet();
        List<User> list = new ArrayList<>();
        while (result.next()) {
            long id = result.getLong("id");
            String name = result.getString("name");
            String email = result.getString("email");
            String dateOfBirth = result.getString("dateOfBirth");
            list.add(new User(id, name, email, dateOfBirth));
        }
        result.close();
        stmt.close();
        return list;
    }

    public User getUserById(long id) throws SQLException {
        User user = null;
        PreparedStatement prStmt = connection.prepareStatement("SELECT * FROM users WHERE id=?");
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
        prStmt.close();
        return user;
    }

    public boolean addUser(User user) throws SQLException {
        PreparedStatement prStmt = connection.prepareStatement(
                "INSERT INTO users (name, email, dateOfBirth) VALUES (?, ?, ?)");
        prStmt.setString(1, user.getName());
        prStmt.setString(2, user.getEmail());
        prStmt.setString(3, user.getDateOfBirth());
        boolean isAdded = prStmt.execute();
        prStmt.close();
        return isAdded;
    }

    public boolean deleteUser(long id) throws SQLException {
        PreparedStatement prStmt = connection.prepareStatement("DELETE FROM users WHERE id=?");
        prStmt.setLong(1, id);
        int result = prStmt.executeUpdate();
        prStmt.close();
        return result > 0;
    }

    public boolean editUser(User user) throws SQLException {
        PreparedStatement prStmt = connection.prepareStatement("UPDATE users SET name=?, email=?, dateOfBirth=? WHERE id=?");
        prStmt.setString(1, user.getName());
        prStmt.setString(2, user.getEmail());
        prStmt.setString(3, user.getDateOfBirth());
        prStmt.setLong(4, user.getId());
        int num = prStmt.executeUpdate();
        prStmt.close();

        return num > 0;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                "id bigint auto_increment, " +
                "name varchar(256), " +
                "email varchar(256), " +
                "dateOfBirth varchar(10), " +
                "PRIMARY KEY (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
