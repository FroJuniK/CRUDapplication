package dao;

import util.DBHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO getUserDAO() {
        Properties property = new Properties();
        String daotype = "";
        try (InputStream in = UserDaoFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            property.load(in);
            daotype = property.getProperty("daotype");
        } catch (IOException e) {
            System.err.println("Error!: FileNotFound!");
        }

        UserDAO dao = null;
        if (daotype.equalsIgnoreCase("UserHibernateDAO")) {
            dao= new UserHibernateDAO(DBHelper.getInstance().getConfiguration());
        } else if (daotype.equalsIgnoreCase("UserJdbcDAO")) {
            dao= new UserJdbcDAO(DBHelper.getInstance().getConnection());
        } else {
            System.err.println("Error!: Implementation is not supported!");
        }
        return dao;
    }
}