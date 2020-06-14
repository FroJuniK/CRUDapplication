package dao;

import util.PropertyReader;

public class UserDaoFactory {

    public static UserDAO getUserDAO() {
        String daotype = PropertyReader.getProperties("config.properties").getProperty("daotype");

        UserDAO dao = null;
        if (daotype.equalsIgnoreCase("UserHibernateDAO")) {
            dao = new UserHibernateDAO();
        } else if (daotype.equalsIgnoreCase("UserJdbcDAO")) {
            dao = new UserJdbcDAO();
        } else {
            System.err.println("Error!: Implementation is not supported!");
        }
        return dao;
    }
}