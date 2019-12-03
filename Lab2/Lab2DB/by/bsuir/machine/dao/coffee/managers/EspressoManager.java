package by.bsuir.machine.dao.coffee.managers;

import by.bsuir.machine.beans.coffee.Espresso;

import java.sql.SQLException;
import java.sql.Statement;

public class EspressoManager implements CoffeeManager {
    public void Insert(Espresso coffee, Statement statement, String id, boolean isProfile) throws SQLException {
        String sql;
        if (isProfile) {
            sql = "INSERT INTO espresso (profileId, temperature, coffeeAmount, waterVolume) VALUES ('" +
                    id + "', " + coffee.getTemperature() + ", " + coffee.getCoffeeAmount() + ", " + coffee.getWaterVolume() + ")";
        } else {
            sql = "INSERT INTO espresso (orderId, temperature, coffeeAmount, waterVolume) VALUES ('" +
                    id + "', " + coffee.getTemperature() + ", " + coffee.getCoffeeAmount() + ", " + coffee.getWaterVolume() + ")";
        }
        statement.addBatch(sql);
    }
}
