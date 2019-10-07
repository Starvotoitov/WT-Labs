package machine.service.creator;

import machine.service.coffee.Espresso;
import machine.service.coffee.LatteMacchiato;

public class LatteMacchiatoCreator implements Creator {
    @Override
    public Espresso createCoffee() {
        return new LatteMacchiato();
    }

    @Override
    public Class<? extends Espresso> getCoffeeClass() {
        return LatteMacchiato.class;
    }
}
