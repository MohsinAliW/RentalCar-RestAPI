package warraich.mohsin.technicaltest.searchprocess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import warraich.mohsin.technicaltest.models.Ride;


/**
 * Returns the list of results
 */
public class Output {
	
private static List<Ride> resultsList;
	

	public Output() {
        resultsList = new ArrayList<>();
    }
	
	//Return the results when only DAVE API is used
	public void printResultsWhenSingleSupplier() {

        if (resultsList.size() == 0) {
            System.out.println("No results to display, please try again.");
        }

        for (Ride ride : resultsList) {
            System.out.println(ride.getcar_type() + " - " + ride.getPrice());
        }
	}
	
	//Return results when all three API's are used
	public void printResultsWhenAllSuppliers() {
        for (Map.Entry<String, Integer> entry : Validate.maximumPassengers.entrySet())
        {
            Ride cheapest = getCheapest(entry.getKey());
            if (cheapest != null ) {
                System.out.println(cheapest.getcar_type() + " - " + cheapest.getSupplier() + " - " + cheapest.getPrice());
            }
        }
    }
	
	public List<Ride> getCheapestRides() {

        List<Ride> cheapestRides = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : Validate.maximumPassengers.entrySet())
        {
            Ride cheapest = getCheapest(entry.getKey());

            if (cheapest != null) {
                cheapestRides.add(cheapest);
            }
        }
        return cheapestRides;
    }
	
	//Finds the cheapest supplier for each type of car
	public static Ride getCheapest(String car_type) {

        long minPrice = 99999999;
        Ride cheapestResult = null;

        for (Ride result: resultsList)
        {
            if (result.getcar_type().equals(car_type) && result.getPrice() < minPrice) {
                minPrice = result.getPrice();
                cheapestResult = result;
            }
        }
        return cheapestResult;
    }
	
	//Sort the rides in decreasing order
	public void sortRides() {
        resultsList.sort((o1, o2) -> (o2.getPrice() - o1.getPrice()) > 0 ? 1 : -1);
    }
	
	//Remove car types that do not contain enough room for passengers from results list
	public void removeSmallRides(int numberOfPassengers) {
	     resultsList.removeIf(t -> numberOfPassengers > Validate.maximumPassengers.get(t.getcar_type()));
	}
	 
	public List<Ride> getResultsList() {
	     return resultsList;
	}
	
}
