import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.lang.Math;

//Time zones: http://www.iana.org/time-zones
public class SundialCalculations {
	String fileLocation = "D:\\Documents and Settings\\ethan.smith1\\My Documents\\Old\\timezone.csv";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double latitude = 0;
		double longitude;
		boolean dayLightSavings = false;
		//outputs for the drawing module
		double[] lineAngles = new double[14];
		int[] lineLabels = new int[14];
		double gAngle = 0;
		
		//get lat, long and date from user
		
		/*
		//Find nearest Meridian
		latitude = getMeridianLong(-37);

		//output name of meridian for correctness check
		 * */
		
		
		//calculate lines 
		latitude = 45;
		//dayLightSavings = true;
		
		int centerHour = 0;
		latitude = Math.abs(latitude); //doesn't matter
		gAngle = latitude;
		for(int i = 0; i <= 12; i++) {
			//System.out.println(lineAngles[i]);
		}
		lineAngles  = getHourLineAngles(latitude);
		for(int i = 0; i <= 12; i++) {
			lineLabels[i] = i + 6;
			if(dayLightSavings)
				lineLabels[i]++;
			if(lineLabels[i] > 12)
				lineLabels[i] -= 12;
		}
		
		//correct lines for DST
		
		//correct lines for orbit position

		
		//Check deliverable values
		for(int i = 0; i <= 12; i++) {
			System.out.println(lineLabels[i] + " " + lineAngles[i] + " " + gAngle);
		}
	}
	
	
	//West is considered negative
	public static double getMeridianLong(double longitude) {
		double meridianLong = 0;
		double nearestHour = 0;
		/* We're just gonna assume 15 degrees per meridian for now
		String line;
		//search local file "timezone.csv"
		try {
			//System.out.println(new File("timezone.csv").getAbsolutePath());
			Scanner fTimeZones = new Scanner(new FileReader("zone.tab"));
			//for(int i = 0; i < 5; i++) {
			
			//Skip Text header
			fTimeZones.nextLine();
			while(fTimeZones.findInLine("4230") == null) {
				line = fTimeZones.nextLine();
				//System.out.println(line);
			}
			line = fTimeZones.nextLine();
			System.out.println(line);
			line = fTimeZones.nextLine();
			System.out.println(line);
		}
		catch (Exception e) 
		{
			System.out.println("Error in reading file. " + e);
		}
		*/
		
		/*
		 *  Newfoundland 52.5 W
			Atlantic 60
			Eastern 75
			Central 90
			Mountain 105
			Pacific 120
			Yukon 135
			Alaska-Hawaii 150
			Bering 165
		 */
		
		
		nearestHour = (double)Math.round((longitude/15) * 1) / 1;
		System.out.println(longitude + " " + nearestHour);
		longitude -= nearestHour * 15;
		System.out.println(longitude + " " + nearestHour);
		
		//calc and output string name of nearest meridian
		System.out.println("getMerdianLong complete"); //learn to use assert command
		return meridianLong;
	}
	
	//negative is south
	public static double[] getHourLineAngles(double latitude) {
		double[] angleArray = new double[14];
		
		for(int i = 0; i <= 12; i++) {
			angleArray[i] = atanDegrees(tanDegrees((i - 6) * 15)* sinDegrees(latitude));
			//System.out.println((i + 6) + " " + angleArray[i] + " " + latitude); // + " " + tanDegrees(i * 60)* sinDegrees(latitude));
		}
		return angleArray;
	}
	
	public static double atanDegrees(double tan) {
		return Math.atan(tan)/Math.PI*180;
	}
	public static double sinDegrees(double degrees) {
		return Math.sin(degrees/180*Math.PI);
	}
	public static double tanDegrees(double degrees) {
		return Math.tan(degrees/180*Math.PI);
	}
	


}

