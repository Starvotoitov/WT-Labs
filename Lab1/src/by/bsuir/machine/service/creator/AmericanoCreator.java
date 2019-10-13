package by.bsuir.machine.service.creator;

import by.bsuir.machine.service.coffee.Americano;
import by.bsuir.machine.service.coffee.Espresso;

public class AmericanoCreator implements Creator {
    @Override
    public Espresso createCoffee() {
        return new Americano();
    }

    @Override
    public Class<? extends Espresso> getCoffeeClass() {
        return Americano.class;
    }
}
