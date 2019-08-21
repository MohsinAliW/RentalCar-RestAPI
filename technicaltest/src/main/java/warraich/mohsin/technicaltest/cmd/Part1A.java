package warraich.mohsin.technicaltest.cmd;

import warraich.mohsin.technicaltest.util.*;
import warraich.mohsin.technicaltest.models.*;


public class Part1A {
	
public static void main(String[] args) {
		
		Validate.validateArguments(args);
		
		Search searchEngine = new Search();
        searchEngine.addSupplier(Validate.DAVE_API);
        

        Location pickUp = new Location(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        Location dropOff = new Location(Double.parseDouble(args[2]),Double.parseDouble(args[3]));
        
        Output search;
        
        if (args.length == 5) {
            int numberOfPassengers = Integer.parseInt(args[4]);
            search = searchEngine.newSearch(pickUp, dropOff, numberOfPassengers);
        } else {
            search = searchEngine.newSearch(pickUp, dropOff);
        }
        

        search.printResultsWhenSingleSupplier();       
        
	}

}
