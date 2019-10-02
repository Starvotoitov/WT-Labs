package machine.creator;

import machine.coffee.Espresso;
import machine.creator.Creator;

public class EspressoCreator implements Creator {
    public Espresso createCoffee() {
        return new Espresso();
    }

    public Class<? extends Espresso> getCoffeeClass() {
        return Espresso.class;
    }
}
