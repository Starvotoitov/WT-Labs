package machine.coffee;

import java.io.Serializable;
import machine.coffee.Espresso;

public class Americano extends Espresso implements Serializable {
	final int DEFAULT_DILUTION_WATER_VOLUME = 450;
	
	private int dilutionWaterVolume;
	
	public int getDilutionWaterVolume() {
		return this.dilutionWaterVolume;
	}
	
	public void setDilutionWaterVolume(int newVolume) {
		if (newVolume > 0) {
			this.dilutionWaterVolume = newVolume;
		}
	}
	
	public Americano() {
		dilutionWaterVolume = DEFAULT_DILUTION_WATER_VOLUME;
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
		Americano americano = (Americano)obj;
		if (dilutionWaterVolume != americano.getDilutionWaterVolume()) {
			return false;
		}
		return true;
	}
}