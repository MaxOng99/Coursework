public class BatteryMeter extends Meter {

	private Battery houseBattery;
	private float powerStoredInBattery;
	private float powerDrawnFromBattery;
	private float powerDrawnFromMains;
	private float meterReading;
	private float finalMeterReading;
	private double cost;

	public BatteryMeter(String typeOfUtility, double costPerUnit, float reading) {
		super(typeOfUtility, costPerUnit, reading);
		houseBattery = new Battery();	
	}

	public double report() {
		meterReading = getMeterReading();
		System.out.println("Utility type: " + getUtilityName());
		System.out.println("Units consumed: " + meterReading);
		
		if (meterReading < 0) {
			houseBattery.storeUnits(meterReading);
			powerStoredInBattery = houseBattery.getStoreUnits();

			System.out.println("Power stored in battery: " + powerStoredInBattery);
		}
		else if (meterReading > 0) {
			houseBattery.useCharges(meterReading);
			powerDrawnFromBattery = houseBattery.getPowerDrawnFromBattery();
			finalMeterReading = houseBattery.getFinalMeterReading();

			System.out.println("Power drawn from battery: " + powerDrawnFromBattery);
			System.out.println("Power drawn from mains: " + finalMeterReading);
			System.out.println("Charges in battery left: " + houseBattery.getStoreUnits());
		}

		if (meterReading < 0 || meterReading <= houseBattery.getStoreUnits()) {
			cost = 0;
		}
		else if (meterReading >= 0) {
			cost = getUnitCost() * (finalMeterReading);
		}

		System.out.println("Cost incurred by this utility: " + cost);
		setMeterReadingToZero();
		return cost;
	}
}