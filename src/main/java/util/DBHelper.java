package util;

import model.User;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private static DBHelper helper;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (helper == null) {
            helper = new DBHelper();
        }
        return helper;
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        Properties prop = PropertyReader.getProperties("hibernate.properties");

        configuration.setProperty("hibernate.dialect", prop.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", prop.getProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", prop.getProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", prop.getProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", prop.getProperty("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql", prop.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", prop.getProperty("hibernate.hbm2ddl.auto"));

        return configuration;
    }

    public Connection getConnection() {
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
}