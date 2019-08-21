package warraich.mohsin.technicaltest.util;

import java.util.HashMap;
import java.util.Map;

public class Validate {
	
	public static final String DAVE_API = "https://techtest.rideways.com/dave";
    public static final String ERIC_API = "https://techtest.rideways.com/eric";
    public static final String JEFF_API = "https://techtest.rideways.com/jeff";
    public static final Map<String, Integer> maximumPassengers = new HashMap<>();

    static {
        maximumPassengers.put("STANDARD", 4);
        maximumPassengers.put("EXECUTIVE", 4);
        maximumPassengers.put("LUXURY", 4);
        maximumPassengers.put("PEOPLE_CARRIER", 6);
        maximumPassengers.put("LUXURY_PEOPLE_CARRIER", 6);
        maximumPassengers.put("MINIBUS", 16);
    }
    
    public static boolean validateArguments(String[] args) {

        // check number of arguments.
        if (args.length != 4 && args.length !=5) {
        	printHelpMessage();
            return  false;
        }

        // check geolocations.
        if (!(isDouble(args[0]) && isDouble(args[1]) && isDouble(args[2]) && isDouble(args[3]))) {
        	printHelpMessage();
            return false;
        }

        // check number of passengers
        if (args.length == 5 && !isPositive(args[4])) {
        	printHelpMessage();
            return false;
        }

        return true;
    }

    public static boolean isPositive(String str) {
        try {
            Integer number = Integer.parseInt(str);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void printHelpMessage() {
        System.out.println("Invalid input!");
        System.out.println("Valid arguments example: 23.43 12.34 12.4 12.345 4");
    }

}
