package by.bsuir.machine.beans.coffee;

import java.io.Serializable;
import java.util.Objects;


public class LatteMacchiato extends CoffeeWithMilk implements Serializable, Cloneable {
    private int whippedCreamVolume;

    public LatteMacchiato() {
        whippedCreamVolume = 100;
    }

    public int getWhippedCreamVolume() {
        return this.whippedCreamVolume;
    }

    public void setWhippedCreamVolume(int newVolume) {
        if (newVolume > 0) {
            this.whippedCreamVolume = newVolume;
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
        LatteMacchiato latte = (LatteMacchiato)obj;
        if (whippedCreamVolume != latte.getWhippedCreamVolume()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), whippedCreamVolume);
    }
}
