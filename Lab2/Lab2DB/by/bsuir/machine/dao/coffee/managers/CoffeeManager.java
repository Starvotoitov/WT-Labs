package by.bsuir.machine.dao.coffee.managers;

import by.bsuir.machine.beans.coffee.Espresso;

import java.sql.SQLException;
import java.sql.Statement;

public interface CoffeeManager {
    void Insert(Espresso coffee, Statement statement, String id, boolean isProfile) throws SQLException;
}
