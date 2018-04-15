package Utils;

import java.sql.*;
import java.util.Properties;

public class MySQLConnector {

    //JDBC Driver + Db URL
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectplanner?autoReconnect=true&useSSL=false";

    //Database connection user
    private static final String USERNAME = "javaApp";
    private static final String PASSWORD = "javaApp";

    //Max pooled connection
    private static final String MAX_POOL = "250";

    //Db Connection
    private static Connection connection = null;
    //Object properties
    private static Properties properties = null;

    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
