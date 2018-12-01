public class Main {
	public static void main (String[] args) {

		String configurationFile;
		int numberOfLines = 0;
		Toolbox myToolbox = new Toolbox();
		configurationFile = myToolbox.readStringFromCmd();
		ConfigurationFileReader configFileReader = new ConfigurationFileReader(configurationFile);	
	}		
}