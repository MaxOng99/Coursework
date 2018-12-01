import java.util.Random;

public class CyclicVaries extends Appliance {

	private Random range = new Random();
	private float minUnitsConsumed;
	private float maxUnitsConsumed;
	private float randomUnitsConsumedPerHour;
	private int activity;
	private int hoursPassed;
	//on/off status of appliance 
	private boolean status;

	//cv = CyclicVaries
	public CyclicVaries(String cvApplianceName, float cvMin, float cvMax, int cvActivity) {
		super(cvApplianceName);
		minUnitsConsumed = cvMin;
		maxUnitsConsumed = cvMax;
		hoursPassed = 0;
		activity = cvActivity;
	}

	public void timePasses() {
		if (hoursPassed >= 24) {
			hoursPassed = 0;
		}

		hoursPassed++;
	
		if (hoursPassed <= activity) {
			status = true;
			float randomUnitsConsumedPerHour = minUnitsConsumed + range.nextFloat() * (maxUnitsConsumed - minUnitsConsumed);
			tellMeterToConsumeUnits(randomUnitsConsumedPerHour);
		}
		else if (hoursPassed > activity){
			status = false;
		}
	}
}