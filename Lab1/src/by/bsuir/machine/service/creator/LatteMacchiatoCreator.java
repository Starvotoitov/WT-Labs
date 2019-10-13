package by.bsuir.machine.service.creator;

import by.bsuir.machine.service.coffee.Espresso;
import by.bsuir.machine.service.coffee.LatteMacchiato;

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
