package artemisLite.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import artemisLite.Zone;
import artemisLite.eSpaceSystem;

class AreaTest {
	
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
		Zone test = new Zone(validSystem, validName, validLocation, validIsPlayerOnElement);
		assertEquals(validSystem, test.getSystem());
		assertEquals(validName, test.getName());
		assertEquals(validLocation, test.getLocation());
	}
	
	@Test
	void testValidName() {
		Zone test = new Zone();
		test.setName(validName);
		assertEquals(validName, test.getName());
	}
	
	@Test
	void testInvalidName() {
		Zone test = new Zone();
		assertThrows(IllegalArgumentException.class, ()-> {
			test.setName(invalidName);
		});
	}
	
	@Test
	void testValidSystem() {
		Zone test = new Zone();
		test.setSystem(validSystem);
		assertEquals(validSystem, test.getSystem());
	}
	
	@Test
	void testValidLocation() {
		Zone test = new Zone();
		test.setLocation(validLocation);
		assertEquals(validLocation, test.getLocation());
	}
	
	@Test
	void testInvalidLocation() {
		Zone test = new Zone();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setLocation(invalidLocation);
		});
	}
	
	@Test
	void testValidIsPlayerOnElement() {
		Zone test = new Zone();
		test.setPlayerOnElement(validIsPlayerOnElement);
		assertEquals(validIsPlayerOnElement, test.isPlayerOnElement());
	}

}
