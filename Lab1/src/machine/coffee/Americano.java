package machine.coffee;

import machine.coffee.Espresso;

public class Americano extends Espresso{
	private int dilutionWaterVolume;
	
	public int getDilutionWaterVolume {
		return this.dilutionWaterVolume;
	}
	
	public void setDilutionWaterVolume(int newVolume) {
		this.dilutionWaterVolume = newVolume;
	}
}