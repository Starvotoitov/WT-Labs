package machine.service.creator;

import machine.service.coffee.Cappuccino;
import machine.service.coffee.Espresso;

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
