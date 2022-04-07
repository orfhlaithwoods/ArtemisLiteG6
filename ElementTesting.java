/**
 * 
 */
package artemisLite.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import artemisLite.Element;
import artemisLite.Player;
import artemisLite.eSpaceSystem;

/**
 * @author caolanegan
 *
 */
class ElementTesting {
	
	/**
	 * Setting up testing variables
	 */
	private String nameValid, nameInvalid;
	private eSpaceSystem systemValid;
	private int locationValid, locationInvalid;
	private int blueprintCostValid, blueprintCostInvalid;
	private int devOneCostValid, devOneCostInvalid;
	private int devTwoCostValid, devTwoCostInvalid;
	private int devThreeCostValid, devThreeCostInvalid;
	private int blueprintFeeValid, bluePrintFeeInvalid;
	private int devOneFeeValid, devOneFeeInvalid;
	private int devTwoFeeValid, devTwoFeeInvalid;
	private int devThreeFeeValid, devThreeFeeInvalid;
	private int currentFeeValid, currentFeeInvalid;
	private int developmentLevelValid, developmentLevelInvalid;
	private int maxInGroupValid, maxInGroupInvalid;
	private boolean validIsPlayerOnElement;
	private Player ownerValid, ownerInvalid;
	private Player sysOwnerValid;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		// Initialising test variables with test data
		nameValid = "Advanced SLS Boosters";
		nameInvalid = "";
		systemValid = eSpaceSystem.SPACE_LAUNCH_SYSTEM;
		locationValid = 2;
		locationInvalid = 15;
		blueprintCostValid = 100;
		blueprintCostInvalid = -100;
		devOneCostValid = 100;
		devOneCostInvalid = -100;
		devTwoCostValid = 100;
		devTwoCostInvalid = -100;
		devThreeCostValid = 100;
		devThreeCostInvalid = -100;
		blueprintFeeValid = 50;
		bluePrintFeeInvalid = -50;
		devOneFeeValid = 100;
		devOneFeeInvalid = -100;
		devTwoFeeValid = 100;
		devTwoFeeInvalid = -100;
		devThreeFeeValid = 100;
		devThreeFeeInvalid = -100;
		currentFeeValid = 100;
		currentFeeInvalid = - 100;
		developmentLevelValid = 1;
		developmentLevelInvalid = -1;
		maxInGroupValid = 2;
		maxInGroupInvalid = -2;
		validIsPlayerOnElement = true;
		Player ownerValid = new Player();
		Player sysOwner = new Player();
	}
	
	@Test
	void testConstructorWithValidArgs() {
		Element testElement = new Element(systemValid, nameValid, locationValid, sysOwnerValid, blueprintCostValid, devOneCostValid, devTwoCostValid, devThreeCostValid, blueprintFeeValid, devOneFeeValid, devTwoFeeValid, devThreeFeeValid, currentFeeValid, developmentLevelValid, ownerValid, maxInGroupValid, validIsPlayerOnElement);
		
		assertEquals(eSpaceSystem.SPACE_LAUNCH_SYSTEM, testElement.getSystem());
		assertEquals(nameValid, testElement.getName());
		assertEquals(locationValid, testElement.getLocation());
		assertEquals(ownerValid, testElement.getOwner());
		assertEquals(blueprintCostValid, testElement.getblueprintCost());
		assertEquals(devOneCostValid, testElement.getdevOneCost());										
		assertEquals(devTwoCostValid, testElement.getdevTwoCost());
		assertEquals(devThreeCostValid, testElement.getdevThreeCost());
		assertEquals(blueprintFeeValid, testElement.getblueprintFee());
		assertEquals(devOneFeeValid, testElement.getdevOneFee());
		assertEquals(devTwoFeeValid, testElement.getdevTwoFee());
		assertEquals(devThreeFeeValid, testElement.getDevThreeFee());
		assertEquals(currentFeeValid, testElement.getCurrentFee());
		assertEquals(developmentLevelValid, testElement.getDevelopmentLevel());
		assertEquals(maxInGroupValid, testElement.getMaxInGroup());
		assertEquals(validIsPlayerOnElement, testElement.isPlayerOnElement());
	}
	
	@Test
	void testSpaceSystem() {
		Element test = new Element();
		test.setSystem(systemValid);
		assertEquals(systemValid, test.getSystem());
	}
	
	@Test
	void testValidName() {
		Element test = new Element();
		test.setName(nameValid);
		assertEquals(nameValid, test.getName());
	}
	
	@Test
	void testInvalidName() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setName(nameInvalid);
		});
	}
	
	@Test
	void testValidLocation() {
		Element test = new Element();
		test.setLocation(locationValid);
		assertEquals(locationValid, test.getLocation());
	}
	
	@Test
	void testInvalidLocation() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setLocation(locationInvalid);
		});
	}
	/*
	@Test
	void testValidOwner() {
		Element test = new Element();
		test.setOwner(ownerValid);
		assertEquals(ownerValid, test.getOwner());
	}*/
	
	@Test
	void testInvalidOwner() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setOwner(ownerInvalid);
		});
	}
	
	@Test
	void testValidSysOwner() {
		Element test = new Element();
		test.setSysOwner(sysOwnerValid);
		assertEquals(sysOwnerValid, test.getSysOwner());
	}
	
	/*
	@Test
	void testInvalidSysOwner() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()-> {
			test.setSysOwner(sysOwnerInvalid);
		});
	}*/
	
	@Test
	void testValidBlueprintCost() {
		Element test = new Element();
		test.setblueprintCost(blueprintCostValid);
		assertEquals(blueprintCostValid, test.getblueprintCost());
	}
	
	@Test
	void testInvalidBlueprintCost() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setblueprintCost(blueprintCostInvalid);
		});
	}
	
	@Test
	void testValidDevOneCost() {
		Element test = new Element();
		test.setdevOneCost(devOneCostValid);
		assertEquals(devOneCostValid, test.getdevOneCost());
	}
	
	@Test
	void testInvalidDevOneCost() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setdevOneCost(devOneCostInvalid);
		});
	}
	
	@Test
	void testValidDevTwoCost() {
		Element test = new Element();
		test.setdevTwoCost(devTwoCostValid);
		assertEquals(devTwoCostValid, test.getdevTwoCost());
	}
	
	@Test
	void testInvalidDevTwoCost() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setdevTwoCost(devTwoCostInvalid);
		});
	}
	
	@Test
	void testValidDevThreeCost() {
		Element test = new Element();
		test.setdevThreeCost(devThreeCostValid);
		assertEquals(devThreeCostValid, test.getdevThreeCost());
	}
	
	@Test
	void testInvalidDevThreeCost() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setdevThreeCost(devThreeCostInvalid);
		});
	}
	
	@Test
	void testValidBlueprintFee() {
		Element test = new Element();
		test.setblueprintFee(blueprintFeeValid);
		assertEquals(blueprintFeeValid, test.getblueprintFee());
	}
	
	@Test
	void testInvalidBlueprintFee() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setblueprintFee(bluePrintFeeInvalid);
		});
	}
	
	@Test
	void testValidDevOneFee() {
		Element test = new Element();
		test.setdevOneFee(devOneFeeValid);
		assertEquals(devOneFeeValid, test.getdevOneFee());
	}
	
	@Test
	void testInvalidDevOneFee() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setdevOneFee(devOneFeeInvalid);
		});
	}
	
	@Test
	void testValidDevTwoFee() {
		Element test = new Element();
		test.setdevTwoFee(devOneFeeValid);
		assertEquals(devOneFeeValid, test.getdevTwoFee());
	}
	
	@Test
	void testInvalidDevTwoFee() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setdevTwoFee(devTwoFeeInvalid);
		});
	}
	
	@Test
	void testValidDevThreeFee() {
		Element test = new Element();
		test.setDevThreeFee(devThreeFeeValid);
		assertEquals(devThreeFeeValid, test.getDevThreeFee());
	}
	
	@Test
	void testInvalidDevThreeFee() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setDevThreeFee(devThreeFeeInvalid);
		});
	}
	
	@Test
	void testValidCurrentFee() {
		Element test = new Element();
		test.setCurrentFee(currentFeeValid);
		assertEquals(currentFeeValid, test.getCurrentFee());
	}
	
	@Test
	void testInvalidCurrentFee() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setCurrentFee(currentFeeInvalid);
		});
	}
	
	@Test
	void testValidDevelopmentLevel() {
		Element test = new Element();
		test.setDevelopmentLevel(developmentLevelValid);
		assertEquals(developmentLevelValid, test.getDevelopmentLevel());
	}
	
	@Test
	void testInvalidDevelopmentLevel() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setDevelopmentLevel(developmentLevelInvalid);
		});
	}
	
	@Test
	void testValidMaxInGroup() {
		Element test = new Element();
		test.setMaxInGroup(maxInGroupValid);
		assertEquals(maxInGroupValid, test.getMaxInGroup());
	}
	
	@Test
	void testInvalidMaxInGroup() {
		Element test = new Element();
		assertThrows(IllegalArgumentException.class, ()->{
			test.setMaxInGroup(maxInGroupInvalid);
		});
	}
	
	@Test
	void testValidIsPlayerOnElement() {
		Element test = new Element();
		test.setPlayerOnElement(validIsPlayerOnElement);
		assertEquals(validIsPlayerOnElement, test.isPlayerOnElement());
	}

	

}
