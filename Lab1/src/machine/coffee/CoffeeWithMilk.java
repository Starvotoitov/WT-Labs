package machine.coffee;

import java.io.Serializable;
import machine.coffee.Espresso;

public class CoffeeWithMilk extends Espresso implements Serializable {
	final int DEFAULT_MILK_VOLUME = 110;
	
	private int milkVolume;
	
	public int getMilkVolume() {
		return this.milkVolume;
	}
	
	public void setMilkVolume(int newVolume) {
		if (newVolume > 0) {
			this.milkVolume = newVolume;
		}
	}
	
	public CoffeeWithMilk() {
		milkVolume = DEFAULT_MILK_VOLUME;
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
		CoffeeWithMilk coffee = (CoffeeWithMilk)obj;
		if (milkVolume != coffee.getMilkVolume()) {
			return false;
		}
		return true;
	}
}