public abstract class Appliance {
	private String name;
	private Meter meter;

	public Appliance(String applianceName) {
		name = applianceName;
	}

	// meterType can be water or electric meter
	public void setMeter(Meter meterType) {
		meter = meterType;
	}

	public abstract void timePasses();

	protected void tellMeterToConsumeUnits(float units) {
		meter.consumeUnits(units);
	}

	public String getName() {
		return name;
	}
}