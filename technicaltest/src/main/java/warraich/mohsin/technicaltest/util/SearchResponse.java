package warraich.mohsin.technicaltest.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import warraich.mohsin.technicaltest.models.Ride;

public class SearchResponse {
	
private List<Ride> resultsList;
	
	public SearchResponse() {
        resultsList = new ArrayList<>();
    }
	
	public void printResultsWhenSingleSupplier() {

        if (resultsList.size() == 0) {
            System.out.println("There are no results to display.");
            System.out.println("This might be because the server failed to give a valid response");
            System.out.println("Rerun the program to try again.");
        }

        for (Ride ride : resultsList) {
            System.out.println(ride.getcar_type() + " --- " + ride.getPrice());
        }
	}
	
	public List<Ride> returnList() {
		return resultsList;
	}
    
	
	public void printResultsWhenAllSuppliers() {

        for (Map.Entry<String, Integer> entry : Utilities.maxPassengersOf.entrySet())
        {
            Ride cheapest = getCheapest(entry.getKey());

            if (cheapest != null ) {
                System.out.println(cheapest.getcar_type() + " ----- " + cheapest.getSupplier() + " ---- " + cheapest.getPrice());
            }
        }

    }
	
	public List<Ride> getCheapestRides() {

        List<Ride> cheapestRides = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : Utilities.maxPassengersOf.entrySet())
        {
            Ride cheapest = getCheapest(entry.getKey());

            if (cheapest != null) {
                cheapestRides.add(cheapest);
            }
        }

        return cheapestRides;

    }
	
	public Ride getCheapest(String car_type) {

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

	
	public void sortPriceDescending() {
        resultsList.sort((o1, o2) -> (o2.getPrice() - o1.getPrice()) > 0 ? 1 : -1);
    }
	
	public void removeIrrelevantResults(int numberOfPassengers) {
	     resultsList.removeIf(o -> numberOfPassengers > Utilities.maxPassengersOf.get(o.getcar_type()));
	}
	 
	public List<Ride> getResultsList() {
	     return resultsList;
	}
	

	public void setResultsList(List<Ride> resultsList) {
	    this.resultsList = resultsList; 
	}

}
