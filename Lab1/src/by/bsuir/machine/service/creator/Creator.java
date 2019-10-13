package by.bsuir.machine.service.creator;

import by.bsuir.machine.service.coffee.Espresso;

public interface Creator {
    Class<? extends Espresso> getCoffeeClass();
    Espresso createCoffee();
}
