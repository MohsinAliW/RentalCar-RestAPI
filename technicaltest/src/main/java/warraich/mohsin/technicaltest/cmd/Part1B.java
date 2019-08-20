package warraich.mohsin.technicaltest.cmd;

import warraich.mohsin.technicaltest.models.*;
import warraich.mohsin.technicaltest.util.*;

public class Part1B {

	public static void main(String[] args) {
		
		if (!Utilities.validateArguments(args)) {
            Utilities.printHelpMessage();
            System.exit(0);
        }

        SearchEngine searchEngine = new SearchEngine();
        searchEngine.addSupplier(Utilities.DAVE_API);
        searchEngine.addSupplier(Utilities.ERIC_API);
        searchEngine.addSupplier(Utilities.JEFF_API);

        Location pickUp = new Location(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        Location dropOff = new Location(Double.parseDouble(args[2]), Double.parseDouble(args[3]));

        SearchResponse searchResponse;
        if (args.length == 5) {
            int numberOfPassengers = Integer.parseInt(args[4]);
            searchResponse = searchEngine.newSearch(pickUp, dropOff, numberOfPassengers);
        } else {
            searchResponse = searchEngine.newSearch(pickUp, dropOff);
        }
        
        searchResponse.printResultsWhenAllSuppliers();

        
	}

}
