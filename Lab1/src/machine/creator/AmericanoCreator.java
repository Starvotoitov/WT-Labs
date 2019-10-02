package machine.creator;

import machine.coffee.Americano;
import machine.coffee.Espresso;
import machine.creator.Creator;

public class AmericanoCreator implements Creator {
    public Espresso createCoffee() {
        return new Americano();
    }

    public Class<? extends Espresso> getCoffeeClass() {
        return Americano.class;
    }
}
