package artemisLite;

import java.util.ArrayList;

public class Board {
	// int for the amount of purchasable elements on the board
	public static final int PROPERTYBOARD_SIZE = 10;
	// instantiates a player object called NASA which will be default owner of all
	// properties at start of game
	public static Player nasa = new Player("nasa");
	// intitialises the movementBoard arraylist
	public static ArrayList<Square> movementBoard = new ArrayList<>();
	// initialises the propertyBoard arrayList
	static ArrayList<Element> propertyBoard = new ArrayList<>();
	/**
	 * data used to populate game board
	 */
	
	// Spacesystem, name, location, systemOwner, blueprintCost, dev1Cost, dev2Cost, dev3Cost, blueprintFee, dev1Fee, dev2Fee, dev3Fee, currentFee, developementLevel, ElementOwner
	
	
	public static NeutralElement a1 = new NeutralElement(eSpaceSystem.NEUTRAL, "START", 1, false);
	
	private static Element a2 = new Element(eSpaceSystem.SPACE_LAUNCH_SYSTEM, "Advanced SLS Boosters", 2, nasa, 200, 200, 200, 200, 50, 100, 200, 300, 0, 0, nasa, 2, false);
	
	private static Element a3 = new Element(eSpaceSystem.SPACE_LAUNCH_SYSTEM, "Upgraded RL10C-X Engines", 3, nasa, 200, 200,
			200, 200, 50, 100, 200, 300, 0, 0, nasa, 2, false);
	private static Element a4 = new Element(eSpaceSystem.ORION_SPACECRAFT, "Crew Module Space Capsule", 4, nasa, 100, 100,
			100, 100, 25, 50, 100, 200, 0, 0, nasa, 3, false);
	private static Element a5 = new Element(eSpaceSystem.ORION_SPACECRAFT, "Solar Panels", 5, nasa, 100, 100, 100, 100, 25,
			50, 100, 200, 0, 0, nasa, 3, false);
	private static Element a6 = new Element(eSpaceSystem.ORION_SPACECRAFT, "Automated Docking System", 6, nasa, 100, 100, 100,
			100, 25, 50, 100, 200, 0, 0, nasa, 3, false);
	public static NeutralElement a7 = new NeutralElement(eSpaceSystem.NEUTRAL, "NASA HQ", 7, false);
	private static Element a8 = new Element(eSpaceSystem.LANDING_SYSTEMS, "Starship Human Landing System", 8, nasa, 200, 200,
			200, 200, 50, 100, 200, 300, 0, 0, nasa, 2, false);
	private static Element a9 = new Element(eSpaceSystem.LANDING_SYSTEMS, "HERACLES Lander", 9, nasa, 200, 200, 200, 200, 50,
			100, 200, 300, 0, 0, nasa, 2, false);
	private static Element a10 = new Element(eSpaceSystem.ARTEMIS_BASE_CAMP, "Foundational Space Habitat", 10, nasa, 100, 100,
			100, 100, 25, 50, 100, 200, 0, 0, nasa, 3, false);
	private static Element a11 = new Element(eSpaceSystem.ARTEMIS_BASE_CAMP, "Habitable Mobility Platform", 11, nasa, 100,
			100, 100, 100, 25, 50, 100, 200, 0, 0, nasa, 3, false);
	private static Element a12 = new Element(eSpaceSystem.ARTEMIS_BASE_CAMP, "Lunar Terrain Vehicle", 12, nasa, 100, 100, 100,
			100, 25, 50, 100, 200, 0, 0, nasa, 3, false);

	/**
	 * movementBoard method: populates and returns the movementBoard
	 * 
	 * @return movementBoard
	 */
	public static ArrayList<Square> movementBoard() {
		// adds each area object to the movementBoard ArrayList
		movementBoard.add(a1);
		movementBoard.add(a2);
		movementBoard.add(a3);
		movementBoard.add(a4);
		movementBoard.add(a5);
		movementBoard.add(a6);
		movementBoard.add(a7);
		movementBoard.add(a8);
		movementBoard.add(a9);
		movementBoard.add(a10);
		movementBoard.add(a11);
		movementBoard.add(a12);
		return movementBoard;
	}

	/**
	 * propertyBoard method: populates and returns the propertyBoard
	 * this is all of the elements on the board that can be purchased and developed by the players
	 * @return propertyBoard
	 */
	public static ArrayList<Element> propertyBoard() {
		// adds each Element object to the propertyBoard ArrayList
		propertyBoard.add(a2);
		propertyBoard.add(a3);
		propertyBoard.add(a4);
		propertyBoard.add(a5);
		propertyBoard.add(a6);
		propertyBoard.add(a8);
		propertyBoard.add(a9);
		propertyBoard.add(a10);
		propertyBoard.add(a11);
		propertyBoard.add(a12);
		// returns the propertyBoard
		return propertyBoard;
	}

	/**
	 * checkArea method: acesses current player square. If property square check
	 * ownership status
	 * 
	 * @param currentPlayer
	 * @param bank
	 * @param board
	 */
	public static boolean checkZone(Player currentPlayer) {
		// sets the endGame boolean as false
		boolean endGame = false;
		
		// getting current area of the player
		Square currentArea = movementBoard.get(currentPlayer.getPosition());
		
		// check current square is Go
		if (currentArea.getName().equalsIgnoreCase("NASA HQ")) {
			System.out.println();
			System.out.println("You have called into NASA HQ to provide a progress report to your director...");
			System.out.println();
			endGame = false;
		} else if (currentArea.getName().equalsIgnoreCase("START")) {
			System.out.println();
			System.out.println("You have landed on the START square.");
			System.out.println();
			endGame = false;
		} else {
			Element currentElement = (Element) currentArea;
			// check if current area available to buy
			if (currentElement.getOwner() == nasa) {
				GameAdmin.buyBlueprint(currentPlayer, currentElement);
				endGame = false;
			} else if (currentElement.getOwner() == currentPlayer) {
				// manage resources method
				System.out.println();
				System.out.println("You own this element.");
			} else {
				// invoke payFee method
				System.out.println();
				System.out.println("---------------------------------------------------------");
				System.out.println("You have landed on an area owned by another player!");
				System.out.println("You must now make a contribution to their research...");
				System.out.println("---------------------------------------------------------");
				System.out.println();
				System.out.println("Area Owner:\t" + currentElement.getOwner().getName());
				endGame = GameAdmin.payFee(currentPlayer, currentElement);
			}
		}
		// returns the endGame boolean value
		return endGame;
	}
}
