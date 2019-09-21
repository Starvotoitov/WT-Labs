package machine.coffee;

import machine.coffee.Espresso;

public class CoffeeWithMilk extends Espresso {
	private int milkVolume;
	
	public int getMilkVolume {
		return this.milkVolume;
	}
	
	public void setMilkVolume(int newVolume) {
		this.milkVolume = newVolume;
	}
}