package artemisLite.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import artemisLite.Square;
import artemisLite.eSpaceSystem;

class SquareTest {
	
	private eSpaceSystem validSystem;
	private String validName, invalidName;
	private int validLocation, invalidLocation;
	private boolean validIsPlayerOnElement;

	@BeforeEach
	void setUp() throws Exception {
		validSystem = eSpaceSystem.ARTEMIS_BASE_CAMP;
		validName = "Valid Name";
		invalidName = "";
		validLocation = 5;
		invalidLocation = 15;
		validIsPlayerOnElement = true;
	}

	@Test
	void testConstructorWithArgs() {
		Square test = new Square(validSystem, validName, validLocation, validIsPlayerOnElement);
		assertEquals(validSystem, test.getSystem());
		assertEquals(validName, test.getName());
		assertEquals(validLocation, test.getLocation());
	}
	
	@Test
	void testValidName() {
		Square test = new Square();
		test.setName(validName);
		assertEquals(validName, test.getName());
	}
	
	@Test
	void testInvalidName() {
		Square test = new Square();
		assertThrows(IllegalArgumentException.class, ()-> {
			test.setName(invalidName);
		});
	}
	
	@Test
	void testValidSystem() {
		Square test = new Square();
		test.setSystem(validSystem);
		assertEquals(validSystem, test.getSystem());
	}
	
	@Test
	void testValidLocation() {
		Square test = new Square();
		test.setLocation(validLocation);
		assertEquals(validLocation, test.getLocation());
	}
	
	@Test
	void testInvalidLocation() {
		Square test = new Square();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setLocation(invalidLocation);
		});
	}
	
	@Test
	void testValidIsPlayerOnElement() {
		Square test = new Square();
		test.setPlayerOnElement(validIsPlayerOnElement);
		assertEquals(validIsPlayerOnElement, test.isPlayerOnElement());
	}

}
