package artemisLite;

public class Area {

	/*
	 * 
	 * 
	 * 
	 * instance vars
	 * 
	 * 
	 * 
	 */

	private eSpaceSystem system;

	private String name;

	private int location;
	
	

	/**
	 */

	public static final int MIN_SYSTEM_BOUNDARY = 0;

	public static final int MAX_SYSTEM_BOUNDARY = 12;

	// System default constructor

	public Area() {

	}

	/**
	 * 
	 * System argument constructor
	 *@param system
	 *@param name
	 * @param location
	 * 
	 * 
	 * 
	 */

	public Area(eSpaceSystem system, String name, int location) {

		this.system = system;

		this.setName(name);

		this.setLocation(location);
		
		

	}

	public eSpaceSystem getSystem() {

		return system;

	}

	public void setSystem(eSpaceSystem system) {

		this.system = system;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) throws IllegalArgumentException {

		if (name != null && !name.isEmpty()) {

			this.name = name;

		} else {

			throw new IllegalArgumentException("Please enter a valid name");

		}

	}

	public int getLocation() {

		return location;

	}

	/**

	 * Sets the System location of the board between the min and max System
	 * boundaries
 
	 * @param location

	 * @throws IllegalArgumentException

	 */

	public void setLocation(int location) throws IllegalArgumentException {

		if (location > MIN_SYSTEM_BOUNDARY && location <= MAX_SYSTEM_BOUNDARY) {

			this.location = location;

		} else {

			throw new IllegalArgumentException("Location out of bounds");

		}

	}

	

	

}