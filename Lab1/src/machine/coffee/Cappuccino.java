package machine.coffee;

import machine.coffee.CoffeeWithMilk;

public class Cappuccino extends CoffeeWithMilk {
	private int milkFoamVolume;
	
	public int getMilkFoamVolume {
		return this.milkFoamVolume;
	}
	
	public void setMilkFoamVolume(int newVolume) {
		this.milkFoamVolume = newVolume;
	}
}