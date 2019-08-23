package warraich.mohsin.technicaltest.searchprocess;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains methods needed to validate a request by making sure that data has been entered correctly
 */
public class Validate {
	
	public static final String DAVE = "https://techtest.rideways.com/dave";
    public static final String ERIC = "https://techtest.rideways.com/eric";
    public static final String JEFF = "https://techtest.rideways.com/jeff";
    
    
  //Contains name and maximum passengers for each type of car
    public static final Map<String, Integer> maximumPassengers = new HashMap<>();

    //Add the car types to maximumPassengers
    static {
        maximumPassengers.put("STANDARD", 4);
        maximumPassengers.put("EXECUTIVE", 4);
        maximumPassengers.put("LUXURY", 4);
        maximumPassengers.put("PEOPLE_CARRIER", 6);
        maximumPassengers.put("LUXURY_PEOPLE_CARRIER", 6);
        maximumPassengers.put("MINIBUS", 16);
    }
    
    
    /**
     * 
     * @param args The command line arguments, containing the pickup and dropoff locations
     * 				and possibly the number of passengers
     * @return		return true if the input is valid
     */
    public static boolean validateArguments(String[] args) {

        // check number of arguments.
        if (args.length != 4 && args.length !=5) {
        	printHelpMessage();
            return  false;
        }

        // check locations to make sure they are doubles
        if (!(isDouble(args[0]) && isDouble(args[1]) && isDouble(args[2]) && isDouble(args[3]))) {
        	printHelpMessage();
            return false;
        }

        // check if number of passengers is a positive integer
        if (args.length == 5 && !isPositive(args[4])) {
        	printHelpMessage();
            return false;
        }

        return true;
    }

    //Check if value is positive
    public static boolean isPositive(String str) {
        try {
            Integer number = Integer.parseInt(str);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Check if value is a double
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
