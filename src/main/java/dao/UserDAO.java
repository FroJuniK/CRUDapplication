package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
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
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM users WHERE id=" + id);
        ResultSet result = stmt.getResultSet();
        if (result.next()) {
            String name = result.getString("name");
            String email = result.getString("email");
            String dateOfBirth = result.getString("dateOfBirth");
            return new User(id, name, email, dateOfBirth);
        } else {
            return null;
        }
    }

    public boolean addUser(User user) throws SQLException {
        Statement stmt = connection.createStatement();
        boolean isAdded = stmt.execute("INSERT INTO users (name, email, dateOfBirth) VALUES ('" +
                user.getName() + "', '" +
                user.getEmail() + "', '" +
                user.getDateOfBirth() + "')");
        return isAdded;
    }

    public boolean deleteUser(long id) throws SQLException {
        Statement stmt = connection.createStatement();
        int result = stmt.executeUpdate("DELETE FROM users WHERE id=" + id);
        stmt.close();
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

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").           //login
                    append("password=root").        //password
                    append("&serverTimezone=UTC").  //setup server time
                    append("&useSSL=false");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
