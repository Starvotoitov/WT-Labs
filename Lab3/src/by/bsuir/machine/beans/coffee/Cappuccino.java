package by.bsuir.machine.beans.coffee;

import java.io.Serializable;
import java.util.Objects;

public class Cappuccino extends CoffeeWithMilk implements Serializable, Cloneable {
    private int milkFoamVolume;

    public int getMilkFoamVolume() {
        return this.milkFoamVolume;
    }

    public void setMilkFoamVolume(int newVolume) {
        if (newVolume > 0) {
            this.milkFoamVolume = newVolume;
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
        Cappuccino cappuccino = (Cappuccino)obj;
        if (milkFoamVolume != cappuccino.getMilkFoamVolume()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), milkFoamVolume);
    }
}
