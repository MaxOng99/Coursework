public class Battery {

	private float capacity;
	private float storeUnits;
	private float wastedEnergy;
	private float finalMeterReading;
	private float powerDrawnFromBattery;

	public Battery() { 
		capacity = 2000;
		storeUnits = 0;
	}	

	public void storeUnits(float meterReading) {
		float sum = storeUnits - meterReading;

		if (storeUnits < capacity && sum < capacity) {
			storeUnits -= meterReading;
		}
		else if (storeUnits < capacity && sum >= capacity) {
			storeUnits = capacity;
		}
	}	

	public void useCharges(float meterReading) {
		finalMeterReading = meterReading;

		if (storeUnits > 0) {
			if (finalMeterReading >= storeUnits) {
				finalMeterReading -= storeUnits; 
				powerDrawnFromBattery = storeUnits;
				storeUnits = 0;
			}

			else if (finalMeterReading < storeUnits) {
				storeUnits -= finalMeterReading;
				powerDrawnFromBattery = finalMeterReading;
				finalMeterReading = 0;
			}
		}

		else if (storeUnits == 0) {
			powerDrawnFromBattery = 0;
		}
	}

	public float getStoreUnits() {
		return storeUnits;
	}

	public float getPowerDrawnFromBattery() {
		return powerDrawnFromBattery;
	}

	public float getFinalMeterReading() {
		return finalMeterReading;
	}
}