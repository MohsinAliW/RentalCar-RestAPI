package warraich.mohsin.technicaltest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import warraich.mohsin.technicaltest.models.Ride;
import warraich.mohsin.technicaltest.searchprocess.Output;

public class OutputTest {
	
	@Before
	public void setup() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	Output searchResponse = new Output();
	
	
	@Test
    public void testSortPriceDescendingEmptyResults() {

        searchResponse.sortRides();

        assertEquals(0, searchResponse.getResultsList().size());

    }


    @Test
    public void testRemoveIrrelevantResultsRemove() {
    	
        Ride ride = new Ride("MINIBUS", 10, "dave");

        searchResponse.getResultsList().add(ride);

        searchResponse.removeSmallRides(20);

        assertEquals(0, searchResponse.getResultsList().size());
    }


}
