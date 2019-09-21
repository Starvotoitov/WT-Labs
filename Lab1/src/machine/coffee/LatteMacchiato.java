package machine.coffee;

import machine.coffee.CoffeeWithMilk;

public class LatteMacchiato extends CoffeeWithMilk {
	private int whippedCreamVolume;
	
	public int getWhippedCreamVolume {
		return this.whippedCreamVolume;
	}
	
	public void setWhippedCreamVolume(int newVolume) {
		this.whippedCreamVolume = newVolume;
	}
}