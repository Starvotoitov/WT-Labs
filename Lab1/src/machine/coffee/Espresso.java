package machine.coffee;

public class Espresso {
	private int temperature;
	private int coffeeAmount;
	private int waterVolume;
	
	public int getTemperature {
		return this.temperature;
	}
	
	public void setTemperature(int newTemperature) {
		this.temperature = newTemperature;
	}
	
	public int getCoffeeAmount {
		return this.coffeeAmount;
	}
	
	public void setCoffeeAmount(int newAmount) {
		this.coffeeAmount = newAmount;
	}
	
	public int getWaterVolume {
		return this.waterVolume;
	}
	
	public void setWaterVolume(int newVolume) {
		this.waterVolume = newVolume;
	}
}