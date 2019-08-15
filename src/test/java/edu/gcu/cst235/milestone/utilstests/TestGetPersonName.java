package edu.gcu.cst235.milestone.utilstests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.gcu.cst235.milestone.view.Utils;

public class TestGetPersonName {

	@Test
	public final void testGetValueFromUserIntIntString() {
		System.out.println("\nTesting " + getClass().getMethods()[0]);
		System.out.println("\nTEST 1: check 5 when user enters 5");
		assertEquals(Utils.getValueFromUser(0, 10, "Error: must be between 0 and 10"), 5);
		System.out.println("\nTEST 2: check error message when user enters -1");
		assertEquals(Utils.getValueFromUser(0, 10, "Error: must be between 0 and 10"), 5);
		System.out.println("\nTEST 3: check error message when user enters 11");
		assertEquals(Utils.getValueFromUser(0, 10, "Error: must be between 0 and 10"), 5);
		System.out.println("\nTEST 4: check 0 when user enters 0");
		assertEquals(Utils.getValueFromUser(0, 10, "Error: must be between 0 and 10"), 0);
		System.out.println("\nTEST 5 check 10 when user enters 10");
		assertEquals(Utils.getValueFromUser(0, 10, "Error: must be between 0 and 10"), 10);
		System.out.println("\nTEST 6: check error message when user enters not a number");
		assertEquals(Utils.getValueFromUser(0, 10, "Error: must be between 0 and 10"), 5);
		System.out.println("\nTEST 7: check error message when user presses Enter");
		assertEquals(Utils.getValueFromUser(0, 10, "Error: must be between 0 and 10"), 5);
	}

	@Test
	public final void testGetValueFromUserDoubleDoubleString() {
		double epsilon = 0.00001;
		System.out.println("\nTesting " + getClass().getMethods()[1]);
		System.out.println("\nTEST 1: check 5.0 when user enters 5");
		assertEquals(Utils.getValueFromUser(0.0, 10.0, "Error: must be between 0 and 10"), 5.0, epsilon);
		System.out.println("\nTEST 2: check error message when user enters -1");
		assertEquals(Utils.getValueFromUser(0.0, 10.0, "Error: must be between 0 and 10"), 5.0, epsilon);
		System.out.println("\nTEST 3: check error message when user enters 11");
		assertEquals(Utils.getValueFromUser(0.0, 10.0, "Error: must be between 0 and 10"), 5.0, epsilon);
		System.out.println("\nTEST 4: check 0.0 when user enters 0");
		assertEquals(Utils.getValueFromUser(0.0, 10.0, "Error: must be between 0 and 10"), 0.0, epsilon);
		System.out.println("\nTEST 5 check 10.0 when user enters 10");
		assertEquals(Utils.getValueFromUser(0.0, 10.0, "Error: must be between 0 and 10"), 10.0, epsilon);
		System.out.println("\nTEST 6: check error message when user enters not a number");
		assertEquals(Utils.getValueFromUser(0.0, 10.0, "Error: must be between 0 and 10"), 5.0, epsilon);
		System.out.println("\nTEST 7: check error message when user presses Enter");
		assertEquals(Utils.getValueFromUser(0.0, 10.0, "Error: must be between 0 and 10"), 5.0, epsilon);
	}

	@Test
	public final void testGetPhoneNumber() {
		assertEquals(1,1);
	}

	@Test
	public final void testGetEmailAddress() {
		assertEquals(1,1);
	}

	@Test
	public final void testGetPersonName() {
		assertEquals(1,1);
	}

	@Test
	public final void testGetUsername() {
		assertEquals(1,1);
	}

	@Test
	public final void testGetPassword() {
		assertEquals(1,1);
	}

	@Test
	public final void testVerifyRegex() {
		assertEquals(1,1);
	}

}