package by.bsuir.machine.dao.coffee.managers;

import by.bsuir.machine.beans.coffee.Espresso;
import by.bsuir.machine.beans.coffee.LatteMacchiato;

import java.sql.SQLException;
import java.sql.Statement;

public class LatteMacchiatoManager implements CoffeeManager {
    public void Insert(Espresso coffee, Statement statement, String id, boolean isProfile) throws SQLException {
        LatteMacchiato coffeeValue = (LatteMacchiato)coffee;
        String sql;
        if (isProfile) {
            sql = "INSERT INTO espresso (profileId, temperature, coffeeAmount, waterVolume, milkVolume, whippedCreamVolume) VALUES ('" +
                    id + "', " + coffeeValue.getTemperature() + ", " + coffeeValue.getCoffeeAmount() + ", " + coffeeValue.getWaterVolume() +
                    ", " + coffeeValue.getMilkVolume() + ", " + coffeeValue.getWhippedCreamVolume() + ")";
        } else {
            sql = "INSERT INTO espresso (orderId, temperature, coffeeAmount, waterVolume, milkVolume, whippedCreamVolume) VALUES ('" +
                    id + "', " + coffeeValue.getTemperature() + ", " + coffeeValue.getCoffeeAmount() + ", " + coffeeValue.getWaterVolume() +
                    ", " + coffeeValue.getMilkVolume() + ", " + coffeeValue.getWhippedCreamVolume() + ")";
        }
        statement.addBatch(sql);
    }
}
