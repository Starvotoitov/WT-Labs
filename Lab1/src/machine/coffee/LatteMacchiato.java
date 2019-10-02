package machine.coffee;

import java.io.Serializable;
import machine.coffee.CoffeeWithMilk;

public class LatteMacchiato extends CoffeeWithMilk implements Serializable, Cloneable {
    private int whippedCreamVolume;

    public LatteMacchiato() {
        whippedCreamVolume = 100;
    }

  /*  public LatteMacchiato(LatteMacchiato toCopy) {
        super(toCopy);
        whippedCreamVolume = toCopy.whippedCreamVolume;
    } */

    public int getWhippedCreamVolume() {
        return this.whippedCreamVolume;
    }

    public void setWhippedCreamVolume(int newVolume) {
        if (newVolume > 0) {
            this.whippedCreamVolume = newVolume;
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

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
}
