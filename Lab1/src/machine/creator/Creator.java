package machine.creator;

import machine.coffee.Espresso;

public interface Creator {
    Class<? extends Espresso> getCoffeeClass();
    Espresso createCoffee();
}
