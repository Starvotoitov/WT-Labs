package by.bsuir.machine.dao;

import by.bsuir.machine.dao.coffee.managers.*;
import by.bsuir.machine.beans.coffee.*;

import java.util.HashMap;

public class CoffeeManagersRepository {
    private HashMap<String, CoffeeManager> repository;

    public CoffeeManagersRepository() {
        repository = new HashMap<String, CoffeeManager>();
        repository.put(Espresso.class.toString(), new EspressoManager());
        repository.put(Americano.class.toString(), new AmericanoManager());
        repository.put(CoffeeWithMilk.class.toString(), new CoffeeWithMilkManager());
        repository.put(Cappuccino.class.toString(), new CappuccinoManager());
        repository.put(LatteMacchiato.class.toString(), new LatteMacchiatoManager());
    }

    public CoffeeManager getManager(String coffeeType) {
        return repository.get(coffeeType);
    }
}
