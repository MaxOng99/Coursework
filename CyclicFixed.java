public class CyclicFixed extends Appliance {

	private float unitsConsumedPerHour;
	private int activity;
	private int hoursPassed;
	private boolean status;

	//CF = cyclic fixed
	public CyclicFixed(String cfApplianceName, float cfUnitsConsumedPerHour, int cfActivity) {
		super(cfApplianceName);
		unitsConsumedPerHour = cfUnitsConsumedPerHour;
		hoursPassed = 0;
		
		if (activity >= 1 || activity <= 24) {
			activity = cfActivity;
		}
		else {
			System.out.println("Please select a number between 1 and 24");
		}
	}

	public void timePasses() {
		if (hoursPassed >= 24) {
			hoursPassed = 0;
		}
		else {
			hoursPassed++;

			if (hoursPassed <= activity) {
				status = true;
				tellMeterToConsumeUnits(unitsConsumedPerHour);
			}
			else if (hoursPassed > activity) {
				status = false;
			}
		}
	}
}