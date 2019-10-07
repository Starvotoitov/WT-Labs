package machine.service.creator;

import machine.service.coffee.Espresso;

public class EspressoCreator implements Creator {
    @Override
    public Espresso createCoffee() {
        return new Espresso();
    }

    @Override
    public Class<? extends Espresso> getCoffeeClass() {
        return Espresso.class;
    }
}
