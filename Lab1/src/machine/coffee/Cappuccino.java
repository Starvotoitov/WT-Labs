package machine.coffee;

import java.io.Serializable;
import machine.coffee.CoffeeWithMilk;

public class Cappuccino extends CoffeeWithMilk implements Serializable {	
	private int milkFoamVolume;
	
	public int getMilkFoamVolume() {
		return this.milkFoamVolume;
	}
	
	public void setMilkFoamVolume(int newVolume) {
		if (newVolume > 0) {
			this.milkFoamVolume = newVolume;
		}
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
		Cappuccino cappuccino = (Cappuccino)obj;
		if (milkFoamVolume != cappuccino.getMilkFoamVolume()) {
			return false;
		}
		return true;
	}
}