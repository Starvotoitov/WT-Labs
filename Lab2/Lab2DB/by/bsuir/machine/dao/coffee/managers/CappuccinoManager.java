package by.bsuir.machine.dao.coffee.managers;

import by.bsuir.machine.beans.coffee.Cappuccino;
import by.bsuir.machine.beans.coffee.Espresso;
import java.sql.SQLException;
import java.sql.Statement;

public class CappuccinoManager implements CoffeeManager {
    public void Insert(Espresso coffee, Statement statement, String id, boolean isProfile) throws SQLException {
        Cappuccino coffeeValue = (Cappuccino) coffee;
        String sql;
        if (isProfile) {
            sql = "INSERT INTO cappuccino (profileId, temperature, coffeeAmount, waterVolume, milkVolume, milkFoamVolume) VALUES ('" +
                    id + "', " + coffeeValue.getTemperature() + ", " + coffeeValue.getCoffeeAmount() + ", " + coffeeValue.getWaterVolume() +
                    ", " + coffeeValue.getMilkVolume() + ", " +coffeeValue.getMilkFoamVolume() + ")";
        } else {
            sql = "INSERT INTO cappuccino (orderId, temperature, coffeeAmount, waterVolume, milkVolume, milkFoamVolume) VALUES ('" +
                    id + "', " + coffeeValue.getTemperature() + ", " + coffeeValue.getCoffeeAmount() + ", " + coffeeValue.getWaterVolume() +
                    ", " + coffeeValue.getMilkVolume() + ", " + coffeeValue.getMilkFoamVolume() + ")";
        }
        statement.addBatch(sql);
    }
}
