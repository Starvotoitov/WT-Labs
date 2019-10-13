package by.bsuir.machine.service.creator;

import by.bsuir.machine.service.coffee.Cappuccino;
import by.bsuir.machine.service.coffee.Espresso;

public class CappuccinoCreator implements Creator {
    @Override
    public Espresso createCoffee() {
        return new Cappuccino();
    }

    @Override
    public Class<? extends Espresso> getCoffeeClass() {
        return Cappuccino.class;
    }
}
