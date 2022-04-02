package artemisLite;

/**
*This Class is used to identify the elements which are neutral on the board
*START and NASA HQ 
*^ these are our neutral elements
* they are different as they cannot be purchased or developed by the players
* when a player passes Start they are credited with BTC
* when a player lands on or passes NASA HQ nothing happens.
*/

public class NeutralElement extends Area {

	/**
	 * NeutralArea default constructor
	 */
	public NeutralElement() {

	} // end of NeutralElements default constructor

	/**
	 * NeutralElements argument constructor
	 * inherits variables from the Area Super Class
	 * 
	 * @param eSpaceSystem - ENUM class defining system names
	 * @param name - ELEMENT name
	 * @param location - Location of element on the board
	 */
	public NeutralElement(eSpaceSystem esystem, String name, int location) {

		// inherited attributes from Area superclass
		super(esystem, name, location);

	} // end of NeutralArea argument constructor
	

} // end of NeutralArea subclass

