package machine.service.coffee;

import java.io.Serializable;
import java.util.Objects;

import machine.annotation.CoffeeInfo;
import machine.annotation.FieldInfo;

@CoffeeInfo(name = "Капучино")
public class Cappuccino extends CoffeeWithMilk implements Serializable, Cloneable {
    @FieldInfo(name = "Объем молочной пены", isRepresented = true)
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
