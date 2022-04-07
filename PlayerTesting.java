/**
 * 
 */
package artemisLite.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import artemisLite.Player;

/**
 * @author caolanegan
 *
 */
class PlayerTesting {
	
	private String validName, invalidName;
	private int validBtc, invalidBtc;
	private int validPosition, invalidPosition;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		validName = "ValidName";
		invalidName = "a";
		validBtc = 100;
		invalidBtc = -100;
		validPosition = 5;
		invalidPosition = 20;
	}

	@Test
	void testConstructorWithArgs() {
		Player test = new Player(validName, validBtc, validPosition);
		
		assertEquals(validName, test.getName());
		assertEquals(validBtc, test.getBtc());
		assertEquals(validPosition, test.getPosition());
	}
	
	@Test
	void testValidName() {
		Player test = new Player();
		test.setName(validName);
		assertEquals(validName, test.getName());
	}
	
	@Test
	void testInvalidName() {
		Player test = new Player();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setName(invalidName);
		});
	}
	
	@Test
	void testValidBtc() {
		Player test = new Player();
		test.setBtc(validBtc);
		assertEquals(validBtc, test.getBtc());
	}
	
	@Test
	void testInvalidBtc() {
		Player test = new Player();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setBtc(invalidBtc);
		});
	}
	
	@Test
	void testValidPosition() {
		Player test = new Player();
		test.setPosition(validPosition);
		assertEquals(validPosition, test.getPosition());
	}
	
	@Test
	void testInvalidPosition() {
		Player test = new Player();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setPosition(invalidPosition);
		});
	}

}
