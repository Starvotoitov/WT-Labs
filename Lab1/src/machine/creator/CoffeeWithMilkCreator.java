package machine.creator;

import machine.coffee.CoffeeWithMilk;
import machine.coffee.Espresso;
import machine.creator.Creator;

public class CoffeeWithMilkCreator implements Creator {
    public Espresso createCoffee() {
        return new CoffeeWithMilk();
    }

    public Class<? extends Espresso> getCoffeeClass() {
        return CoffeeWithMilk.class;
    }
}
