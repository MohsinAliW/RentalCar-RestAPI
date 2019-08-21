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
	
	Output searchResponse = new Output();
	
	@RequestMapping("/query")
	public @ResponseBody String getAllTopics(@RequestParam("pickup") String pickup, @RequestParam("dropoff") String dropoff, @RequestParam("passengers") String numberofPassengers) {
		Search searchEngine = new Search();
        searchEngine.addSupplier(Validate.DAVE_API);
        searchEngine.addSupplier(Validate.ERIC_API);
        searchEngine.addSupplier(Validate.JEFF_API);
        
        Location pickUpLocation = extractLocation(pickup);
        Location dropOffLocation = extractLocation(dropoff);
        
        Output searchResponse;
        
        searchResponse = searchEngine.newSearch(pickUpLocation, dropOffLocation,Integer.parseInt(numberofPassengers));
        
        return new Gson().toJson(searchResponse.getCheapestRides());
        
        
        
	}

	 static Location extractLocation(String location) {

	        String[] coordinates = location.split(",");

	        if (!(Validate.isDouble(coordinates[0]) && Validate.isDouble(coordinates[1]))) {
	            return null;
	        }

	        return new Location(Double.parseDouble(coordinates[0]),Double.parseDouble(coordinates[1]));
	 }

}
