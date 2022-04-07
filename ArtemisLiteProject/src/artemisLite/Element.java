package artemisLite;

public class Element extends Square {

	/**
	 * instance vars
	 */
	private int blueprintCost;
	private int devOneCost;
	private int devTwoCost;
	private int devThreeCost;
	private int blueprintFee;
	private int devOneFee;
	private int devTwoFee;
	private int devThreeFee;
	private int currentFee;
	private int developmentLevel;
	private int maxInGroup;
	
	private Player owner;
	private Player sysOwner;
	

	/**
	 * minimum & maximum element development level
	 */
	public static final int MIN_DEV_LEVEL = 0;
	public static final int MAX_DEV_LEVEL = 4;

	/**
	 * Element default constructor
	 */
	public Element() {

	} // end of Element default constructor

	/**
	 * 
	 * @param eSystem
	 * @param name
	 * @param location
	 * @param blueprintCost
	 * @param devOneCost
	 * @param devTwoCost
	 * @param devThreeCost
	 * @param blueprintFee
	 * @param devOneFee
	 * @param devTwoFee
	 * @param devThreeFee
	 * @param currentFee
	 * @param developmentLevel
	 * @param owner
	 * @param systemOwner
	 */
	public Element(eSpaceSystem system, String name, int location, Player sysOwner, int blueprintCost, int devOneCost, int devTwoCost,
			int devThreeCost, int blueprintFee, int devOneFee, int devTwoFee, int devThreeFee, int currentFee,
			int developmentLevel, Player owner, int maxInGroup, boolean isPlayerOnElement) {

		// inherited attributes from Area superclass
		super(system, name, location, isPlayerOnElement);
																			
		this.setblueprintCost(blueprintCost);
		this.setdevOneCost(devOneCost);
		this.setdevTwoCost(devTwoCost);
		this.setdevThreeCost(devThreeCost);
		this.setblueprintFee(blueprintFee);
		this.setdevOneFee(devOneFee);
		this.setdevTwoFee(devTwoFee);
		this.setDevThreeFee(devThreeFee);
		this.setCurrentFee(currentFee);
		this.setDevelopmentLevel(developmentLevel);
		this.owner = owner;
		this.setSysOwner(sysOwner);
		this.setMaxInGroup(maxInGroup);
		

	} // end of Element argument constructor

	/**
	 * getblueprintCost method
	 * 
	 * @return the blueprint
	 */
	public int getblueprintCost() {
		return blueprintCost;
	}

	/**
	 * setblueprintCost method - throws IllegalArgumentException if resource below
	 * zero has been entered
	 * 
	 * @param blueprint the blueprint to set
	 */
	public void setblueprintCost(int blueprintCost) throws IllegalArgumentException {
		if (blueprintCost > 0) {
			this.blueprintCost = blueprintCost;
		} else {
			throw new IllegalArgumentException("Invalid resource value");
		}
	}

	/**
	 * getdevOneCost method
	 * 
	 * @return the developmentPrice
	 */
	public int getdevOneCost() {
		return devOneCost;
	}

	/**
	 * setdevelopmentPrice method - throws IllegalArgumentException if resource
	 * below zero has been entered
	 * 
	 * @param developmentPrice the developmentPrice to set
	 */
	public void setdevOneCost(int devOneCost) throws IllegalArgumentException {
		if (devOneCost > 0) {
			this.devOneCost = devOneCost;
		} else {
			throw new IllegalArgumentException("Invalid resource value");
		}
	}

	/**
	 * getdevOneCost method
	 * 
	 * @return the developmentPrice
	 */
	public int getdevTwoCost() {
		return devTwoCost;
	}

	/**
	 * setdevelopmentPrice method - throws IllegalArgumentException if resource
	 * below zero has been entered
	 * 
	 * @param developmentPrice the developmentPrice to set
	 */
	public void setdevTwoCost(int devTwoCost) throws IllegalArgumentException {
		if (devTwoCost > 0) {
			this.devTwoCost = devTwoCost;
		} else {
			throw new IllegalArgumentException("Invalid resource value");
		}

	}

	/**
	 * getdevThreeCost method
	 * 
	 * @return the developmentPrice
	 */
	public int getdevThreeCost() {
		return devThreeCost;
	}

	/**
	 * setdevThreeCost method - throws IllegalArgumentException if resource below
	 * zero has been entered
	 * 
	 * @param devThreeCost the devThreeCost to set
	 */
	public void setdevThreeCost(int devThreeCost) throws IllegalArgumentException {
		if (devThreeCost > 0) {
			this.devThreeCost = devThreeCost;
		} else {
			throw new IllegalArgumentException("Invalid resource value");
		}

	}

	/**
	 * getblueprintFee method
	 * 
	 * @return the blueprintFee
	 */
	public int getblueprintFee() {
		return blueprintFee;
	}

	/**
	 * setblueprintFee method - throws IllegalArgumentException if resource below
	 * zero has been entered
	 * 
	 * @param blueprintFee the blueprintFee to set
	 */
	public void setblueprintFee(int blueprintFee) throws IllegalArgumentException {
		if (blueprintFee > 0) {
			this.blueprintFee = blueprintFee;
		} else {
			throw new IllegalArgumentException("Invalid resource value");
		}
	}

	/**
	 * getdevOneFee method
	 * 
	 * @return the devOneFee
	 */
	public int getdevOneFee() {
		return devOneFee;
	}

	/**
	 * setdevOneFee method - throws IllegalArgumentException if resource below zero
	 * has been entered
	 * 
	 * @param devOneFee the devOneFee to set
	 */
	public void setdevOneFee(int devOneFee) throws IllegalArgumentException {
		if (devOneFee > 0) {
			this.devOneFee = devOneFee;
		} else {
			throw new IllegalArgumentException("Invalid resource value");
		}
	}

	/**
	 * getdevTwoFee method
	 * 
	 * @return the devTwoFee
	 */
	public int getdevTwoFee() {
		return devTwoFee;
	}

	/**
	 * setdevTwoFee method - throws IllegalArgumentException if resource below zero
	 * has been entered
	 * 
	 * @param devTwoFee the devTwoFee to set
	 */
	public void setdevTwoFee(int devTwoFee) throws IllegalArgumentException {
		if (devTwoFee > 0) {
			this.devTwoFee = devTwoFee;
		} else {
			throw new IllegalArgumentException("Invalid resource value");
		}
	}

	/**
	 * getdevThreeFee method
	 * 
	 * @return the devThreeFee
	 */

	public int getDevThreeFee() {
		return devThreeFee;
	}

	/**
	 * setdevThreeFee method - throws IllegalArgumentException if resource below
	 * zero has been entered
	 * 
	 * @param devThreeFee the devThreeFee to set
	 */
	public void setDevThreeFee(int devThreeFee) throws IllegalArgumentException {
		if (devThreeFee >= 0) {
			this.devThreeFee = devThreeFee;
		} else {
			throw new IllegalArgumentException("Invalid resource value");
		}
	}

	/**
	 * getCurrentFee method
	 * 
	 * @return the currentFee
	 */
	public int getCurrentFee() {
		return currentFee;
	}

	/**
	 * setCurrentFee method
	 * 
	 * @param currentFee the currentFee to set
	 */
	public void setCurrentFee(int currentFee) {
		if (currentFee >= 0) {
			this.currentFee = currentFee;
		} else {
			throw new IllegalArgumentException();
		}
		
	}

	/**
	 * getDevelopmentLevel method
	 * 
	 * @return the developmentLevel
	 */
	public int getDevelopmentLevel() {
		return developmentLevel;
	}
	
	public void incremenetDevelopmentLevel() {
		this.developmentLevel++;
	}

	/**
	 * setDevelopmentLevel method: sets the development level if the development
	 * level is within the appropriate range, otherwise an IllegalArgumentException
	 * is thrown
	 * 
	 * @param developmentLevel the developmentLevel to set
	 */
	public void setDevelopmentLevel(int developmentLevel) {

		if (developmentLevel >= MIN_DEV_LEVEL && developmentLevel <= MAX_DEV_LEVEL) {
			this.developmentLevel = developmentLevel;
		} else {
			throw new IllegalArgumentException("Invalid development level");
		}

	} // end of setDevelopmentLevel method

	/**
	 * getOwner method
	 * 
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * setOwner method: throws illegal argument if a null owner argument is passed
	 * 
	 * @param owner
	 */
	public void setOwner(Player owner) throws IllegalArgumentException {

		if (owner != null) {

			this.owner = owner;

		} else {

			throw new IllegalArgumentException("No player assigned to Element");
		}
	} 
	
	/**
	 * @return the sysOwner
	 */
	public Player getSysOwner() {
		return sysOwner;
	}

	/**
	 * @param sysOwner the sysOwner to set
	 */
	public void setSysOwner(Player sysOwner) {
		this.sysOwner = sysOwner;
	}

	/**
	 * @return the maxInGroup
	 */
	public int getMaxInGroup() {
		return maxInGroup;
	}

	/**
	 * @param maxInGroup the maxInGroup to set
	 */
	public void setMaxInGroup(int maxInGroup) {
		if (maxInGroup > 0 && maxInGroup < 4) {
			this.maxInGroup = maxInGroup;
		} else {
			throw new IllegalArgumentException();
		}
		
	}

	/**
	 * displayDevelopmentLevel method: takes the developmentLevel int value of the
	 * Element and returns the relevant description for that developmentLevel
	 * description String
	 * 
	 * @return developmentLevel
	 */
	public String displayDevelopmentLevel() {
		int developmentLevelNumber = this.developmentLevel;
		String developmentLevel = "default";

		// switch statement
		switch (developmentLevelNumber) {

		case 0:
			developmentLevel = "Blueprint";
			break;
		case 1:
			developmentLevel = "Phase One";
			break;
		case 2:
			developmentLevel = "Phase Two";
			break;
		case 3:
			developmentLevel = "Phase Three";
			break;
		case 4:
			developmentLevel = "Launch";
			break;

		default:
			developmentLevel = "default";
		} // end of switch statement

		// returns developmentLevel
		return developmentLevel;

	} // end of displayDevelopmentLevel method
	
	/**
	 * Returns the cost of the next development phase according to the space system of the element
	 * @param eSpaceSystem
	 * @return
	 */
	public int displayNextDevCost(eSpaceSystem eSpaceSystem) {
		int nextDevCost;

		if (eSpaceSystem == eSpaceSystem.ORION_SPACECRAFT || eSpaceSystem == eSpaceSystem.ARTEMIS_BASE_CAMP) {
			nextDevCost = 100;
		} else {
			nextDevCost = 200;
		}
		return nextDevCost;
	}

	/**
	 * updateCurrentFee method: changes the currentFee of a Element to devPhase or
	 * launchPhase fee depending on the developmentLevel int value
	 * 
	 * @param investArea
	 */
	public void updateCurrentFee() {

		// if the element developmentLevel will be between 1 and 4 when it reaches the
		// updateCurrentFee method
		if (this.developmentLevel > MIN_DEV_LEVEL && this.developmentLevel < MAX_DEV_LEVEL) {

			// fee calculation for 1 - 3 devPhases
			this.setCurrentFee(this.getdevOneFee() * this.developmentLevel);

		} else {
			// element developmentLevel = 4, hence launchPhase fee is set
			this.setCurrentFee(this.getdevTwoFee());

		}

	} 

	/**
	 * displayAreaDetails method: displays the details of a specific element area
	 * when a player lands on it
	 * 
	 */
	public void displaySpaceSystemDetails() {

		System.out.println("Name:\t\t" + this.getName());
		System.out.println("Space System:\t" + this.getSystem());
		System.out.println("Dev Level:\t" + this.displayDevelopmentLevel());
		System.out.println("Current Fee:\t" + this.getCurrentFee());
		System.out.println("Owner:\t\t" + this.getOwner().getName());
		

	} 

	/**
	 * playerStatusUpdate method: displays the details of a specific element area
	 * during the manageResources method
	 */
	public void playerStatusUpdate() {

		System.out.println("Name: " + this.getName());
		System.out.println("Space System : " + this.getSystem());
		System.out.println("Current Fee: " + this.getCurrentFee());
		System.out.println("Current development lvl: " + this.displayDevelopmentLevel());
		System.out.println("Cost of next development: " + this.displayNextDevCost(getSystem()));

	} // end of playerStatusUpdate method

	
	/**
	 * Displays relevant details about the development phase of an element,
	 * to be displayed to the player after they develop an element
	 */
	public void displayDevelopmentDetails() {
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("\tDevelopment details");
		System.out.println("---------------------------------------------------------");
		System.out.println("Name:\t\t" + this.getName());
		System.out.println("Dev Level:\t" + this.getDevelopmentLevel());
		System.out.println("Fee:\t\t" + this.getCurrentFee());
		System.out.println();
	}


} // end of Element subclass
