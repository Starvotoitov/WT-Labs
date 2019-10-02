package machine.creator;

import machine.coffee.Espresso;
import machine.coffee.LatteMacchiato;
import machine.creator.Creator;

public class LatteMacchiatoCreator implements Creator {
    public Espresso createCoffee() {
        return new LatteMacchiato();
    }

    public Class<? extends Espresso> getCoffeeClass() {
        return LatteMacchiato.class;
    }
}
