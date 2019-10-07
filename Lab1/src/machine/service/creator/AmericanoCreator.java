package machine.service.creator;

import machine.service.coffee.Americano;
import machine.service.coffee.Espresso;

public class AmericanoCreator implements Creator {
    @Override
    public Espresso createCoffee() {
        return new Americano();
    }

    @Override
    public Class<? extends Espresso> getCoffeeClass() {
        return Americano.class;
    }
}
