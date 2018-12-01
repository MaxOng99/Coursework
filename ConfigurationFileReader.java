import java.io.FileReader;
import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ConfigurationFileReader {

	ArrayList<String> linesInFile = new ArrayList<>();
	ArrayList<String> names = new ArrayList<>();
	ArrayList<String> subClass = new ArrayList<>();
	ArrayList<String> meter = new ArrayList<>();
	ArrayList<String> min = new ArrayList<>();
	ArrayList<String> max = new ArrayList<>();
	ArrayList<String> fixed = new ArrayList<>();
	ArrayList<String> probability = new ArrayList<>();
	ArrayList<String> probability2 = new ArrayList<>();
	ArrayList<String> cycle = new ArrayList<>();
	ArrayList<String> cycle2 = new ArrayList<>();
	ArrayList<String> newArray = new ArrayList<>();

	int numberOfLines = 0;
	String line;	
	BufferedReader bufferedReader;	
	Toolbox myToolbox = new Toolbox();

	public ConfigurationFileReader(String fileName) {
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
		}
		catch(FileNotFoundException fnfx) {
			System.out.println(fnfx.getMessage() + " File can't be read, possible that it does not exist");
		}	
		storeLineInArray();
		constructArrays();
	}

	public void storeLineInArray() {
		try {
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().length() > 0) {
					linesInFile.add(line);
					numberOfLines++;
				}
			}
		}
		catch(IOException iox) {
			System.out.println(iox.getMessage());
		}
	}

	public int getTotalLines() {
		return numberOfLines;
	}

	public void constructArrays() {
		for (String data: linesInFile) {
			String[] element = new String[2];
			String[] element2 = new String[2];
			element = data.split(": ");
			if (data.contains("name")) {
				names.add(element[1]);
			}

			else if (data.contains("subclass")) {
				subClass.add(element[1]);
			}

			else if (data.contains("meter")) {
				meter.add(element[1]);
			}

			else if (data.contains("Min")) {
				if (element.length < 2) {
					element2[0] = " ";
					min.add(element2[0]);
				}

				else if (element.length >= 2) {
					min.add(element[1]);
				}
			}

			else if (data.contains("Max")) {
				if (element.length < 2) {
					element2[0] = " ";
					max.add(element2[0]);
				}

				else if (element.length >= 2) {
					max.add(element[1]);
				}
			}

			else if (data.contains("Fixed")) {
				if (element.length < 2) {
					element2[0] = " ";
					fixed.add(element2[0]);
				}

				else if (element.length >= 2) {
					fixed.add(element[1]);
				}
			}

			else if (data.contains("Probability")) {
				if (element.length < 2) {
					element2[0] = " ";
					probability.add(element2[0]);
				}

				else if (element.length >= 2) {
					probability.add(element[1]);
				}
			}

			else if (data.contains("Cycle")) {
				if (element.length < 2) {
					element2[0] = " ";
					cycle.add(element2[0]);
				}

				else if (element.length >= 2) {
					cycle.add(element[1]);
				}
			}
		}

		for(String probz: probability) {
			String[] separated = new String[2];
			String[] separated2 = new String[2];
			if (probz.contains("in")) {
				separated = probz.split(" in ");
				probability2.add(separated[1]);
			}

			else if (probz.equals(" ")) {
				probability2.add(probz);
			}
		}

		for(String length: cycle) {
			String[] separated = new String[2];
			String[] separated2 = new String[2];
			separated = length.split("/");
			if (separated.length < 2) {
				separated2[0] = " ";
				cycle2.add(separated2[0]);
			}

			else if (separated.length >= 2) {
				cycle2.add(separated[0]);	
			}
		}
	}

	public ArrayList<String> getApplianceNames() {
		return names;
	}

	public ArrayList<String> getSubClass() {
		return subClass;
	}

	public ArrayList<String> getMeter() {
		return meter;
	}

	public ArrayList<String> getMinUnitsConsumed() {
		return min;
	}

	public ArrayList<String> getMaxUnitsConsumed() {
		return max;
	}

	public ArrayList<String> getFixedUnitsConsumed() {
		return fixed;
	}

	public ArrayList<String> getProbability() {
		return probability2;
	}

	public ArrayList<String> getCycle() {
		return cycle2;
	}
}












