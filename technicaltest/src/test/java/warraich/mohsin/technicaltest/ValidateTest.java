package warraich.mohsin.technicaltest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import warraich.mohsin.technicaltest.searchprocess.Validate;

public class ValidateTest {

	@Test
    public void testIsDoubleValid() {
        assertTrue(Validate.isDouble("2.2"));
    }
	
	@Test
    public void testIsDoubleValidInteger() {
        assertTrue(Validate.isDouble("2"));
    }
	
	@Test
    public void testIsDoubleValidDoubleEndsIn0() {
        assertTrue(Validate.isDouble("2.0"));
    }

    @Test
    public void testIsDoubleEmptyText() {
        assertFalse(Validate.isDouble(""));
    }

    @Test
    public void testIsDoubleText() {
        assertFalse(Validate.isDouble("dfds"));
    }

    @Test
    public void testIsPositiveIntegerValid() {
        assertTrue(Validate.isPositive("2"));
    }

    @Test
    public void testIsPositiveIntegerEmptyText() {
        assertFalse(Validate.isPositive(""));
    }

    @Test
    public void testIsPositiveIntegerText() {
        assertFalse(Validate.isPositive("asdas"));
    }

    @Test
    public void testIsPositiveIntegerNegativeInteger() {
        assertFalse(Validate.isPositive("-2"));
    }

    @Test
    public void testValidateArgumentsValid() {
        String[] arguments = new String[] { "3.4",  "2" , "5", "2.3", "4" };
        assertTrue(Validate.validateArguments(arguments));
    }


    @Test
    public void testValidateArgumentsNotEnoughArguments() {
        String[] arguments = new String[] { "3.4",  "2" , "5"};
        assertFalse(Validate.validateArguments(arguments));
    }

    @Test
    public void testValidateArgumentsTooManyArguments() {
        String[] arguments = new String[] { "3.4",  "2" , "5", "2.3", "4", "45" };
        assertFalse(Validate.validateArguments(arguments));
    }



}
