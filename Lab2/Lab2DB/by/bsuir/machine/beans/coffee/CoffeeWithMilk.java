package by.bsuir.machine.beans.coffee;

import java.io.Serializable;
import java.util.Objects;

public class CoffeeWithMilk extends Espresso implements Serializable, Cloneable {
    private static final int DEFAULT_MILK_VOLUME = 110;

    private int milkVolume;

    public CoffeeWithMilk() {
        milkVolume = DEFAULT_MILK_VOLUME;
    }

    public int getMilkVolume() {
        return this.milkVolume;
    }

    public void setMilkVolume(int newVolume) {
        if (newVolume > 0) {
            this.milkVolume = newVolume;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (! super.equals(obj)) {
            return false;
        }
        CoffeeWithMilk coffee = (CoffeeWithMilk)obj;
        if (milkVolume != coffee.getMilkVolume()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), milkVolume);
    }
}
