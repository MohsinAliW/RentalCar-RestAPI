package warraich.mohsin.technicaltest.cmd;

import warraich.mohsin.technicaltest.models.*;
import warraich.mohsin.technicaltest.util.*;

public class Part1B {

	public static void main(String[] args) {
		
		Validate.validateArguments(args);

        Search searchEngine = new Search();
        searchEngine.addSupplier(Validate.DAVE_API);
        searchEngine.addSupplier(Validate.ERIC_API);
        searchEngine.addSupplier(Validate.JEFF_API);

        Location pickUp = new Location(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        Location dropOff = new Location(Double.parseDouble(args[2]), Double.parseDouble(args[3]));

        Output search;
        if (args.length == 5) {
            int numberOfPassengers = Integer.parseInt(args[4]);
            search = searchEngine.newSearch(pickUp, dropOff, numberOfPassengers);
        } else {
            search = searchEngine.newSearch(pickUp, dropOff, 0);
        }
        
        search.printResultsWhenAllSuppliers();
        

        
	}

}
