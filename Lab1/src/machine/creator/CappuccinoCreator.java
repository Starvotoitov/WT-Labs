package machine.creator;

import machine.coffee.Cappuccino;
import machine.coffee.Espresso;
import machine.creator.Creator;

public class CappuccinoCreator implements Creator {
    public Espresso createCoffee() {
        return new Cappuccino();
    }

    public Class<? extends Espresso> getCoffeeClass() {
        return Cappuccino.class;
    }
}
