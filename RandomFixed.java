import java.util.Random;

public class RandomFixed extends Appliance {

	private Random random = new Random();
	private float unitsConsumedPerHour;

	//activeProbability: 1 in __ (The integer to be filled is determined by the configuration file)
	private int activeProbability;
	private int hoursPassed;
	private boolean status;

	//rf = RandomFixed
	public RandomFixed(String rfApplianceName, float rfUnitsConsumedPerHour, int rfActiveProbability) {
		super(rfApplianceName);
		unitsConsumedPerHour = rfUnitsConsumedPerHour;
		activeProbability = rfActiveProbability;
		hoursPassed = 0;
	}

	public void timePasses() {
		if (hoursPassed >= 24) {
			hoursPassed = 0;
		}

		hoursPassed++;

		//nextInt(activeProbability) returns a random integer from 0 to (activeProbability - 1)
		if (random.nextInt(activeProbability) == 0) {
			status = true;
			tellMeterToConsumeUnits(unitsConsumedPerHour);
		}
		else {
			status = false;
		}
	}
}