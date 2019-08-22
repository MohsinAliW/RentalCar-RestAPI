package warraich.mohsin.technicaltest.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import warraich.mohsin.technicaltest.models.*;
import warraich.mohsin.technicaltest.searchprocess.Output;
import warraich.mohsin.technicaltest.searchprocess.Search;
import warraich.mohsin.technicaltest.searchprocess.Validate;


@RestController
public class RideController {
	
	Output searchResponse = new Output();
	
	@RequestMapping("/query")
	public @ResponseBody String getAllTopics(@RequestParam("pickup") String pickup, @RequestParam("dropoff") String dropoff, @RequestParam("passengers") String passengers) {
		Search searchEngine = new Search();
        searchEngine.addSupplier(Validate.DAVE);
        searchEngine.addSupplier(Validate.ERIC);
        searchEngine.addSupplier(Validate.JEFF);
        
        Location pickupLocation = extractLocation(pickup);
        Location dropoffLocation = extractLocation(dropoff);
        
        if (pickupLocation == null) {
            return "{\"message\":\"'pickupLocation' parameter has been entered incorrectly. Example of correct input: 2.13,2.79\"}";
        }
        
        if (dropoffLocation == null) {
            return "{\"message\":\"'dropoffLocation' parameter has been entered incorrectly. Example of correct input: 2.13,2.79\"}";
        }
        
        
        
        Output searchResponse;
        
        searchResponse = searchEngine.newSearch(pickupLocation, dropoffLocation,Integer.parseInt(passengers));
        
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
