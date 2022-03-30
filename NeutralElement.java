package artemisLite;

public class NeutralElement extends Area {

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
	public NeutralElement(eSpaceSystem esystem, String name, int location) {

		// inherited attributes from Area superclass
		super(esystem, name, location);

	} // end of NeutralArea argument constructor
	

} // end of NeutralArea subclass

