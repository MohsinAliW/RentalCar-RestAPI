package warraich.mohsin.technicaltest.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import warraich.mohsin.technicaltest.models.*;
import warraich.mohsin.technicaltest.util.*;


@RestController
public class RideController {
	
SearchResponse searchResponse = new SearchResponse();
	
	@RequestMapping("/")
	public @ResponseBody String getAllTopics(@RequestParam("pickup") String pickup, @RequestParam("dropoff") String dropoff, @RequestParam("passengers") String numberofPassengers) {
		SearchEngine searchEngine = new SearchEngine();
        searchEngine.addSupplier(Utilities.DAVE_API);
        searchEngine.addSupplier(Utilities.ERIC_API);
        searchEngine.addSupplier(Utilities.JEFF_API);
        
        Location pickUpLocation = extractLocation(pickup);
        Location dropOffLocation = extractLocation(dropoff);
        
        SearchResponse searchResponse;
        
        searchResponse = searchEngine.newSearch(pickUpLocation, dropOffLocation,Integer.parseInt(numberofPassengers));
        
        return new Gson().toJson(searchResponse.getCheapestRides());
        
        
        
	}
	
	 private static boolean checkPassengers(String numberOfPassengers) {

	        if (numberOfPassengers == null || Utilities.isPositiveInteger(numberOfPassengers)) {
	            return true;
	        }

	        return false;
	 }


	 private static Location extractLocation(String location) {

	        String[] coordinates = location.split(",");

	        if (!(Utilities.isDouble(coordinates[0]) && Utilities.isDouble(coordinates[1]))) {
	            return null;
	        }

	        return new Location(Double.parseDouble(coordinates[0]),Double.parseDouble(coordinates[1]));
	 }

}
