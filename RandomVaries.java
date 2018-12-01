import java.util.Random;

public class RandomVaries extends Appliance {

	private Random random = new Random();
	private float minUnitsConsumed; 
	private float maxUnitsConsumed;
	private float randomUnitsConsumedPerHour;
	private int activeProbability;
	private int hoursPassed;

	//rv = RandomVaries
	public RandomVaries(String rvApplianceName, float rvMin, float rvMax, int rvActiveProbability) {
		super(rvApplianceName);
		minUnitsConsumed = rvMin;
		maxUnitsConsumed = rvMax;
		activeProbability = rvActiveProbability;
		hoursPassed = 0;
	}

	public void timePasses() {
		hoursPassed++;

		if (random.nextInt(activeProbability) == 0) {
			float randomUnitsConsumedPerHour = minUnitsConsumed + random.nextFloat() * (maxUnitsConsumed - minUnitsConsumed);
			tellMeterToConsumeUnits(randomUnitsConsumedPerHour);
		}
	}
}