package by.bsuir.machine.dao.coffee.managers;

import by.bsuir.machine.beans.coffee.Americano;
import by.bsuir.machine.beans.coffee.Espresso;

import java.sql.SQLException;
import java.sql.Statement;

public class AmericanoManager implements CoffeeManager {
    public void Insert(Espresso coffee, Statement statement, String id, boolean isProfile) throws SQLException {
        Americano coffeeValue = (Americano)coffee;
        String sql;
        if (isProfile) {
            sql = "INSERT INTO americano (profileId, temperature, coffeeAmount, waterVolume, dilutionWaterVolume) VALUES ('" +
                    id + "', " + coffeeValue.getTemperature() + ", " + coffeeValue.getCoffeeAmount() + ", " + coffeeValue.getWaterVolume() +
                    ", " + coffeeValue.getDilutionWaterVolume() +")";
        } else {
            sql = "INSERT INTO americano (orderId, temperature, coffeeAmount, waterVolume, dilutionWaterVolume) VALUES ('" +
                    id + "', " + coffeeValue.getTemperature() + ", " + coffeeValue.getCoffeeAmount() + ", " + coffeeValue.getWaterVolume() +
                    ", " + coffeeValue.getDilutionWaterVolume() + ")";
        }
        statement.addBatch(sql);
    }
}
