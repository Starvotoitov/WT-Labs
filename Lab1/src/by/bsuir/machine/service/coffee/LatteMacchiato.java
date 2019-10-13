package by.bsuir.machine.service.coffee;

import java.io.Serializable;
import java.util.Objects;

import by.bsuir.machine.annotation.CoffeeInfo;
import by.bsuir.machine.annotation.FieldInfo;

@CoffeeInfo(name = "Латте Макиато")
public class LatteMacchiato extends CoffeeWithMilk implements Serializable, Cloneable {
    @FieldInfo(name = "Объем взбитых сливок", isRepresented = true)
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
