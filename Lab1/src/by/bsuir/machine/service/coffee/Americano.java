package by.bsuir.machine.service.coffee;

import java.io.Serializable;
import java.util.Objects;

import by.bsuir.machine.annotation.CoffeeInfo;
import by.bsuir.machine.annotation.FieldInfo;

@CoffeeInfo(name = "Американо")
public class Americano extends Espresso implements Serializable, Cloneable {
    @FieldInfo(name = "", isRepresented = false)
    private static final int DEFAULT_DILUTION_WATER_VOLUME = 450;

    @FieldInfo(name = "Объем разбавляющей воды", isRepresented = true)
    private int dilutionWaterVolume;

    public Americano() {
        dilutionWaterVolume = DEFAULT_DILUTION_WATER_VOLUME;
    }

    public int getDilutionWaterVolume() {
        return this.dilutionWaterVolume;
    }

    public void setDilutionWaterVolume(int newVolume) {
        if (newVolume > 0) {
            this.dilutionWaterVolume = newVolume;
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
        Americano americano = (Americano)obj;
        if (dilutionWaterVolume != americano.getDilutionWaterVolume()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dilutionWaterVolume);
    }
}
