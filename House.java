import java.util.ArrayList;
import java.util.Iterator;
import java.text.Format;

public class House {
	private Meter water;
	private Meter electric;
	private ArrayList<Appliance> appliances = new ArrayList<>();
	private double sumOfCosts;
	private float totalMeterReading;
	private int hours;
	private double totalCost = 0;
	private ConfigurationFileReader cfReader;

	public House() {
		electric = new Meter("electric", 0.1, 0);
		water = new Meter("water", 0.05, 0);
	}

	public House(Meter electricMeter, Meter waterMeter) {
		electric = electricMeter;
		water = waterMeter;
	}

	public void addElectricAppliance(Appliance electricAppliance) {
		appliances.add(electricAppliance);
		electricAppliance.setMeter(electric);
	}

	public void addWaterAppliance(Appliance waterAppliance) {
		appliances.add(waterAppliance);
		waterAppliance.setMeter(water);
	}

	public void removeAppliance(Appliance applianceToRemove) {
		Iterator<Appliance> itAppliances = appliances.iterator();

		while (itAppliances.hasNext()) {
			if (itAppliances.next().equals(applianceToRemove)) {
				itAppliances.remove();
			}
		}
	}

	public int numAppliance() {
		return appliances.size();
	}

	//One hour passing everytime activate() is called
	public double activate() {
		for (Appliance appliance: appliances) {
			appliance.timePasses();
		}

		double electricCost = electric.report();
		System.out.println();
		double waterCost = water.report();
		sumOfCosts = waterCost + electricCost; 

		System.out.println();
		System.out.println("Total cost incurred: " + sumOfCosts);
		System.out.println();
		
		return sumOfCosts;
	}

	public void runSimulation(int numOfHours) {
		totalCost = 0;
		for (hours = 1; hours <= numOfHours; hours++) {
			System.out.println("");
			System.out.println("hour " + hours);
			System.out.println("-------");

			for (Appliance appliance: appliances) {
				appliance.timePasses();
			}

			double electricCost = electric.report();
			System.out.println();
			double waterCost = water.report();

			sumOfCosts = waterCost + electricCost; 
			System.out.println();
			System.out.println("Total cost incurred in this hour: " + sumOfCosts);
			System.out.println();

			totalCost+= sumOfCosts;
		}
	}

	public double activate(int numOfHours) { 
		int days = ((numOfHours + 24 - 1) / 24);

		if (days == 1 && numOfHours <= 24) {
			System.out.println("=================");
			System.out.println("     Day 1");
			System.out.println("=================");
			runSimulation(numOfHours);
			System.out.println("Total cost for day 1 is: " + totalCost);
		}

		else if (days >= 2) {
			for (int i = 1; i < days; i++) {
				System.out.println("=================");
				System.out.println("     Day " + i);
				System.out.println("=================");
				runSimulation(24);
				System.out.println("Total cost for day " + i +" is: " + totalCost);
			}
			System.out.println("=================");
			System.out.println("     Day " + days);
			System.out.println("=================");
			runSimulation(numOfHours - ((days - 1)*24));
			System.out.println("Total cost for day " + days +" is: " + totalCost);
		}
		return totalCost;
	}

	public static void main (String args[]) {
		ConfigurationFileReader cfReader = new ConfigurationFileReader(args[0]);
		BatteryMeter houseBM = new BatteryMeter("Electric", 0.5, 0);
		Meter normalMeter = new Meter("Water", 0.05, 0);
		House house1 = new House(houseBM, normalMeter);

		String name;
		String subClass;
		String meter;
		float min;
		float max;
		float fixed;
		int probz;
		int cycle;
		int totalNumberOfHours;

		for (int i = 0; i <= 12; i++) {
			name = cfReader.getApplianceNames().get(i);
			subClass = cfReader.getSubClass().get(i);
			meter = cfReader.getMeter().get(i);

			if (cfReader.getMinUnitsConsumed().get(i).equals(" ")) {
				min = 0.0f;
				System.out.println(min);
			}

			else {
				min = Float.parseFloat(cfReader.getMinUnitsConsumed().get(i));
				System.out.println(min);
			}
			
			if (cfReader.getMaxUnitsConsumed().get(i).equals(" ")) {
				max = 0.0f;
			}
			else {
				max = Float.parseFloat(cfReader.getMaxUnitsConsumed().get(i));
			}

			if (cfReader.getFixedUnitsConsumed().get(i).equals(" ")) {
				fixed = 0.0f;
			}

			else  {
				fixed = Float.parseFloat(cfReader.getFixedUnitsConsumed().get(i));
			}
			if (cfReader.getProbability().get(i).equals(" ")) {
				probz = 0;
			}

			else  {
				probz = Integer.parseInt(cfReader.getProbability().get(i));
			}
			if (cfReader.getCycle().get(i).equals(" ")) {
				cycle = 0;
			}
			else  {
				cycle = Integer.parseInt(cfReader.getCycle().get(i));
			}
			
			if (subClass.equals("CyclicFixed")) {
				if (meter.equals("electric")) {
					house1.addElectricAppliance(new CyclicFixed(name, fixed, cycle));
				}

				else if (meter.equals("water")) {
					house1.addWaterAppliance(new CyclicFixed(name, fixed, cycle));
				}
			}

			else if (subClass.equals("CyclicVaries")) {
				if (meter.equals("electric")) {
					house1.addElectricAppliance(new CyclicVaries(name, min, max, cycle));
				}

				else if (meter.equals("water")) {
					house1.addWaterAppliance(new CyclicVaries(name, min, max, cycle));
				}
			}

			else if (subClass.equals("RandomFixed")) {
				if (meter.equals("electric")) {
					house1.addElectricAppliance(new RandomFixed(name, fixed, probz));
				}

				else if (meter.equals("water")) {
					house1.addWaterAppliance(new RandomFixed(name, fixed, probz));
				}
			}

			else if (subClass.equals("RandomVaries")) {
				if (meter.equals("electric")) {
					house1.addElectricAppliance(new RandomVaries(name, min, max, probz));
				}

				else if (meter.equals("water")) {
					house1.addWaterAppliance(new RandomVaries(name, min, max, probz));
				}
			}
		}

		if (args.length == 1) {
			house1.activate(168);
		}

		else if (args.length == 2 ) {
			int hours = Integer.parseInt(args[1]);
			house1.activate(hours);

		}
	}		
}
