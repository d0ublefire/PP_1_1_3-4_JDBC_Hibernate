package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD ="ROOTgfhjkm1!";

    private static SessionFactory factory;

    private static Connection connection = null;

    public static Connection getConnection() {

        try {
            if (connection == null) {
                Class.forName(DRIVER);
                System.out.println("OK");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch ( ClassNotFoundException | SQLException e) {
            System.out.println("Error in class Util");
            e.printStackTrace();
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {if (factory == null) {
        try {
            Configuration configuration = new Configuration();

            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, DRIVER);
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USERNAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

            settings.put(Environment.SHOW_SQL, "true");

            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


            configuration.setProperties(settings);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        return factory;
    }

}



