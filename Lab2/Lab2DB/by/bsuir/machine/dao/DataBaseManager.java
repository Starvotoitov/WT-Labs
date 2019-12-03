package by.bsuir.machine.dao;

import by.bsuir.machine.beans.coffee.Espresso;
import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.beans.profile.Profile;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseManager {
    private static String URL = "jdbc:mysql://localhost:3306/lab2";
    private static String USER = "root";
    private static String PASSWORD = "root";

    private Logger logger;
    private Connection connection;
    private Statement statement;
    private CoffeeManagersRepository repository;

    public DataBaseManager() {
        repository = new CoffeeManagersRepository();
        PropertyConfigurator.configure("log4j.properties");
        logger = Logger.getLogger(DataBaseManager.class);
    }

    public boolean createConnection() {
        boolean result;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            result = true;
        } catch (SQLException ex) {
            logger.error(ex.getErrorCode() + ": " + ex.getMessage());
            result = false;
        }
        return result;
    }

    public void insertOrders(ArrayList<Order> list) {
        for (Order order : list) {
            try {
                statement.addBatch("INSERT INTO orders(id, creationDate) VALUES ('" + order.getId() + "', " + order.getDate().getTime() + ")");
                repository.getManager(order.getCoffee().getClass().toString()).Insert(order.getCoffee(), statement, order.getId(), false);
                statement.executeBatch();
            } catch (SQLException ex) {
                logger.error(ex.getErrorCode() + ": " + ex.getMessage());
                try {
                    statement.clearBatch();
                } catch (SQLException e) {
                    logger.error(e.getErrorCode() + ": " + e.getMessage());
                }
            }
        }
    }

    public void insertProfiles(ArrayList<Profile> list) {
        for (Profile profile : list) {
            try {
                statement.addBatch("INSERT INTO profiles(id, profileName, creationDate) VALUES ('" + profile.getId() + "', '" + profile.getProfileName() + "', " +
                        profile.getCreationDate().getTime() + ")");
                for (Espresso coffee : profile.getBasicCoffeeSettings()) {
                    repository.getManager(coffee.getClass().toString()).Insert(coffee, statement, profile.getId(), true);
                }
                statement.executeBatch();
            } catch (SQLException ex) {
                logger.error(ex.getErrorCode() + ": " + ex.getMessage());
                try {
                    statement.clearBatch();
                } catch (SQLException e) {
                    logger.error(e.getErrorCode() + ": " + e.getMessage());
                }
            }
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            logger.error(ex.getErrorCode() + ": " + ex.getMessage());
        }
    }
}
