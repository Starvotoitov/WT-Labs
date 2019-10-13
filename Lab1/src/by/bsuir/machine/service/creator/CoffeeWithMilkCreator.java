package by.bsuir.machine.service.creator;

import by.bsuir.machine.service.coffee.CoffeeWithMilk;
import by.bsuir.machine.service.coffee.Espresso;

public class CoffeeWithMilkCreator implements Creator {
    @Override
    public Espresso createCoffee() {
        return new CoffeeWithMilk();
    }

    @Override
    public Class<? extends Espresso> getCoffeeClass() {
        return CoffeeWithMilk.class;
    }
}
