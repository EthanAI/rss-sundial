import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.lang.Math;

//Time zones: http://www.iana.org/time-zones
public class SundialCalculations {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double latitude = 0;
		double longitude = 0;
		int date = 0; //today is 20130411
		boolean dayLightSavings = false;
		
		//outputs for the drawing module
		double[] lineAngles = new double[14];
		int[] lineLabels = new int[14];
		double gAngle = 0;
		
		//get lat, long and date from user
		latitude = getLatitude();
		longitude = getLongitude();
		date = getDate();
		
		//check for daylight savings
		dayLightSavings = isDayLightSavings(latitude, longitude, date);

		//Find nearest Meridian from table (if this is required)

		
		
		//calculate lines and angles
		gAngle = Math.abs(latitude); //Gnomon can't be negative;
		lineAngles  = getHourLineAngles(latitude, longitude, date);
		
		//Set up the labels for each line
		for(int i = 0; i <= 12; i++) { 
			lineLabels[i] = i + 6;  //go from 6 am to 6 pm
			if(dayLightSavings)
				lineLabels[i]++;   //just increase labels by 1 hr to handle DST correction
			if(lineLabels[i] > 12) //Convert military time (18:00) to civilian time (6:00)
				lineLabels[i] -= 12;
		}
		
		//correct lines for orbit position

		
		//Check deliverable values
		for(int i = 0; i <= 12; i++) {
			System.out.println("Hour: " + lineLabels[i] + " \t" + lineAngles[i]);
		}
		System.out.println("Gnomon Angle: \t" + gAngle);
	}
	
	/*
	 * Main calculation. Computes the angles of the lines to draw on the sundial
	 * Compensates for latitude, longitude deviation from the meridian and the EOT time of year variation
	 * (Earth's imperfect orbit etc.) 
	 * 
	 * If you are west of the meridian, the sun will show you a time earlier than what happens at the meridian (add time to correct)
	 * If you are in winter, the sun will show you a time earlier than official watch time at the meridian (add time to correct)
	 * West of the meridian in winter, both correction factors are negative. 
	 * 
	 * North and East are positive
	 */
	public static double[] getHourLineAngles(double latitude, double longitude, int date) {
		double[] angleArray = new double[14];
		double meridianDelta = getMeridianDelta(longitude); //minutes
		double EOTDelta = EOTCorrection(date); //minutes
		double netDelta = (meridianDelta + EOTDelta) / 60; //converted to hours
		//System.out.println(meridianDelta + " " + EOTDelta + " " + netDelta);
		//Lines are symmetrical before correction factors
		//After correction factors in winter, west of meridian (most common situation):
		//  Lines before noon are further from north
		//  Lines after noon are closer to north
		//  Noon is CCW of north
		double hoursFromNoon = -6;
		hoursFromNoon += netDelta; //Make bigger AM angles if modifier is negative (want to show a time later than what the sun shows)
		for(int i = 0; i <= 12; i++, hoursFromNoon++) {
			//System.out.println(hoursFromNoon);
			angleArray[i] = atanDegrees(tanDegrees(hoursFromNoon * 15)* sinDegrees(latitude));
			//correction when angle should be obtuse (atan cannot spit out angle larger than 90)
			if(hoursFromNoon < -6) {
				angleArray[i] = -1 * (180 - angleArray[i]);
			}
			if(hoursFromNoon > 6) {
				angleArray[i] = 180 + angleArray[i];
			}
		}
		return angleArray;
	}
	
	/*
	 * Gets the degrees of difference between current location and the meridian of the
	 * time zone you are currently located in.
	 * Bounds: +/- 7.5 degrees
	 * -7.5 degrees is west of the meridian
	 */
	public static double getMeridianDelta(double longitude) {
		double meridianDelta = 0;
		int nearestHour = 0;
		/* We're just gonna assume 15 degrees per meridian for now
		 * possible code for reading from a table of time zones if needed
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
		
		/*  Possible data for table implementation 
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
		
		nearestHour = (int)Math.round((longitude/15) * 1) / 1; //Calculate hours from GMT
		meridianDelta = longitude - nearestHour * 15; //Removes the hour difference to just leave the minute difference
		meridianDelta = meridianDelta / 15 * 60; //convert from degrees of space to minutes of time
		//System.out.println(nearestHour + " " + meridianDelta);
		return meridianDelta;
	}
	
	/*
	 * Returns the number of minutes to correct the sundial due to 
	 * orbital aberrations. 
	 * Formula from http://www.susdesign.com/popups/sunangle/eot.php
	 * E = 9.87 * sin (2B) - 7.53 * cos (B) - 1.5 * sin (B)
	 * B = 360 * (N - 81) / 365
	 * N = day number, January 1 = day 1	 
	 */
	public static double EOTCorrection(int date) {
		double minutesDelta = 0;
		int dayNumber = getDayNumber(date);
		double B = 360 * (dayNumber - 81) / 365;
		double E = 9.87 * sinDegrees(2*B) - 7.53 * cosDegrees(B) - 1.5 * sinDegrees(B);
		minutesDelta = E;
		return minutesDelta;
	}
	
	/*
	 * getDayNumber(int date){}
	 * Get the number of the day in the year (1-365 or 366)
	 * Seems likely this code will be used in future projects so making a 
	 * separate method for it.
	 * Check vs. http://amsu.cira.colostate.edu/julian.html
	 */
	public static int getDayNumber(int date) {
		int dayNumber = 0;
		int year = 0;
		int month = 0;
		int day = 0;
		boolean isLeapYear = false;
		int[] monthLengthStandard = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		//break date into pieces
		day = date % 100;
		month = (int) (date % 10000) / 100;
		year = (int) date / 10000;

		//check for leapyear
		if((year % 400) == 0)
		   isLeapYear = true;
		else if ((year % 100) == 0)
		   isLeapYear = false;
		else if((year % 4) == 0)
		   isLeapYear = true;
		else
		   isLeapYear = false;
		
		//calculate day of the year
		dayNumber = day;
		for(int i = 0; i < month - 1; i++) {
			dayNumber += monthLengthStandard[i];
		}
		if(isLeapYear && month > 2) //add leap year day 
			dayNumber++;
		return dayNumber;
	}
	
	public static double cosDegrees(double degrees) {
		return Math.cos(degrees/180*Math.PI);
	}
	public static double sinDegrees(double degrees) {
		return Math.sin(degrees/180*Math.PI);
	}
	public static double tanDegrees(double degrees) {
		return Math.tan(degrees/180*Math.PI);
	}
	public static double atanDegrees(double tan) {
		return Math.atan(tan)/Math.PI*180;
	}
	
	public static double getLongitude() {
		return -157;
	}
	
	public static double getLatitude() {
		return 21;
	}
	
	public static int getDate() {
		return 20130415;
	}
	
	public static boolean isDayLightSavings(double latitude, double longitude, int date) {
		boolean DSTBoolean = false;
		try {
			//check if its summer and we're in a location that does DST (S-hemisphere not implemented yet)
			//Also, assuming USA DST dates for global usage
			if(hasDSTLocation(latitude, longitude) && isUSASummer(date)) {
				DSTBoolean = true;
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		if(DSTBoolean)
			System.out.println("Daylight savings is in effect.");
		return DSTBoolean;
	}
	
	/*
	 * Test if it is officially DST summer in the US.
	 * Time of day is ignored (simplification)
	 * Table from http://en.wikipedia.org/wiki/Daylight_saving_time_in_the_United_States
	 */
	public static boolean isUSASummer(int date) throws Exception {
		int[] USADSTSequence = {20070101, 20070311, 20071104, 20080309, 20081102, 20090308, 20091101, 20100314, 20101107, 20110313, 20111106, 20120311, 20121104, 20130310, 20131103, 20140309, 20141102, 20150308, 20151101, 20160313, 20161106, 20170312, 20171105, 20180311, 20181104, 20190310, 20191103, 20200308, 20201101, 20210314, 20211107, 20220313, 20221106, 20230312, 20231105, 20240310, 20241103, 20250309, 20251102, 20251231};
		boolean isSummer = false;
		if(date < 20070101 || date > 20251231) //error if date value is outside our table
			throw new Exception("DST module only works from 2007 to 2025.");
		//Iterate through DST sequence, toggling isSummer each value. When the wheel stops, isSummer will be correct.
		for (int i = 0; i < USADSTSequence.length && date > USADSTSequence[i]; i++, isSummer = !isSummer) {
			//System.out.println(isSummer + " " + date + " " + USADSTSequence[i]);
		}
		isSummer = !isSummer; //final toggle
		return isSummer;
	}
	
	/*
	 * Exclusions from DST:
	 *   Hawaii
	 */
	public static boolean hasDSTLocation(double latitude, double longitude) {
		boolean isDSTLocation = true; //assume only exceptions don't do DST. (True in US and Europe and neither prof. or TA are from Asia)
		double hawaiiNLat = 30;
		double hawaiiSLat = 15;
		double hawaiiELong = -150;
		double hawaiiWLong = -170;

		//check if in hawaii
		if(withinGlobeQuandrant(latitude, longitude, hawaiiNLat, hawaiiSLat, hawaiiWLong, hawaiiELong))
			isDSTLocation = false;
		
		if(!isDSTLocation)
			System.out.println("Your location does not observe daylight savings...ever.");
		return isDSTLocation;
	}
	
	public static boolean withinGlobeQuandrant(double latitude, double longitude, double nLat, double sLat, double wLong, double eLong) {
		return (latitude <= nLat && latitude >= sLat && longitude <= eLong && longitude >= wLong);
	}
}

