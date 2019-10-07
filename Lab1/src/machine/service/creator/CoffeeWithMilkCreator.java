package machine.service.creator;

import machine.service.coffee.CoffeeWithMilk;
import machine.service.coffee.Espresso;

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
