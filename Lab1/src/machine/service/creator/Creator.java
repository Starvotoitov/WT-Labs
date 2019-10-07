package machine.service.creator;

import machine.service.coffee.Espresso;

public interface Creator {
    Class<? extends Espresso> getCoffeeClass();
    Espresso createCoffee();
}
