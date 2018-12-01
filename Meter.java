public class Meter {
	private String utilityName;
	private double unitCost;
	private float meterReading;
	private double totalCost;

	public Meter(String typeOfUtility, double costPerUnit, float initialReading) {
		utilityName = typeOfUtility;
		unitCost = costPerUnit;
		meterReading = initialReading;
	}

	public void consumeUnits(float units) {
			meterReading += units; 
	}

	public double report() {
		totalCost = unitCost * meterReading;

		if (totalCost < 0) {
			totalCost = 0;
		}

		System.out.println("Utility type: " + utilityName);
		System.out.println("Units consumed: " + meterReading);
		System.out.println("Cost incurred by this utility: " + totalCost);
		setMeterReadingToZero();
		return totalCost;
	}

	public String getUtilityName() {
		return utilityName;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public float getMeterReading() {
		return meterReading;
	}

	public void setMeterReadingToZero() {
		meterReading = 0;
	}
}