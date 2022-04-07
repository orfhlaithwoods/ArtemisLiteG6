package artemisLite;

public class NeutralElement extends Zone {
	
	private boolean playerOnElement;
	
	/**
	 * Neutral elements are those elements that the player is unable to purchase and develop (i.e. START and NASA HQ)
	 */

	/**
	 * NeutralArea default constructor
	 */
	public NeutralElement() {

	} // end of NeutralElements default constructor

	/**
	 * NeutralElements argument constructor
	 * 
	 * @param eSpaceSystem
	 * @param name
	 * @param location
	 */
	public NeutralElement(eSpaceSystem esystem, String name, int location, boolean isPlayerOnElement) {

		// inherited attributes from Area superclass
		super(esystem, name, location, isPlayerOnElement);
		

	} // end of NeutralArea argument constructor
	

} // end of NeutralArea subclass

