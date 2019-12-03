package by.bsuir.machine.dao.coffee.managers;

import by.bsuir.machine.beans.coffee.CoffeeWithMilk;
import by.bsuir.machine.beans.coffee.Espresso;

import java.sql.SQLException;
import java.sql.Statement;

public class CoffeeWithMilkManager implements CoffeeManager {
    public void Insert(Espresso coffee, Statement statement, String id, boolean isProfile) throws SQLException {
        CoffeeWithMilk coffeeValue = (CoffeeWithMilk)coffee;
        String sql;
        if (isProfile) {
            sql = "INSERT INTO coffeewithmilk (profileId, temperature, coffeeAmount, waterVolume, milkVolume) VALUES ('" +
                    id + "', " + coffeeValue.getTemperature() + ", " + coffeeValue.getCoffeeAmount() + ", " + coffeeValue.getWaterVolume() +
                    ", " + coffeeValue.getMilkVolume() + ")";
        } else {
            sql = "INSERT INTO coffeewithmilk (orderId, temperature, coffeeAmount, waterVolume, milkVolume) VALUES ('" +
                    id + "', " + coffeeValue.getTemperature() + ", " + coffeeValue.getCoffeeAmount() + ", " + coffeeValue.getWaterVolume() +
                    ", " + coffeeValue.getMilkVolume() + ")";
        }
        statement.addBatch(sql);
    }
}
