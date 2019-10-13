package by.bsuir.machine.service.coffee;

import by.bsuir.machine.annotation.CoffeeInfo;
import by.bsuir.machine.annotation.FieldInfo;

import java.io.Serializable;
import java.util.Objects;

@CoffeeInfo(name = "Эспрессо")
public class Espresso implements Serializable, Cloneable {
    @FieldInfo(name = "", isRepresented = false)
    private static final int DEFAULT_TEMPERATURE = 95;
    @FieldInfo(name = "", isRepresented = false)
    private static final int DEFAULT_COFFEE_AMOUNT = 10;
    @FieldInfo(name = "", isRepresented = false)
    private static final int DEFAULT_WATER_VOLUME = 35;

    @FieldInfo(name = "Температура", isRepresented = true)
    private int temperature;
    @FieldInfo(name = "Объем кофе", isRepresented = true)
    private int coffeeAmount;
    @FieldInfo(name = "Объем воды", isRepresented = true)
    private int waterVolume;

    public Espresso() {
        temperature = DEFAULT_TEMPERATURE;
        coffeeAmount = DEFAULT_COFFEE_AMOUNT;
        waterVolume = DEFAULT_WATER_VOLUME;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public void setTemperature(int newTemperature) {
        if (newTemperature > 0) {
            this.temperature = newTemperature;
        }
    }

    public int getCoffeeAmount() {
        return this.coffeeAmount;
    }

    public void setCoffeeAmount(int newAmount) {
        if (newAmount > 0) {
            this.coffeeAmount = newAmount;
        }
    }

    public int getWaterVolume() {
        return this.waterVolume;
    }

    public void setWaterVolume(int newVolume) {
        if (newVolume > 0) {
            this.waterVolume = newVolume;
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
        Espresso espresso = (Espresso)obj;
        if (temperature != espresso.getTemperature() || coffeeAmount != espresso.getCoffeeAmount()
                || waterVolume != espresso.getWaterVolume()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, coffeeAmount, waterVolume);
    }
}
