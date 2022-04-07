package artemisLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class GameAdmiin {

	
	// object for current player
	public static Player currentPlayer = new Player();
	
	// object for default owner of all elements and systems
	public static Player nasa = new Player();
	public static eSpaceSystem eSpaceSystem;
	
	// Final variables for min and max number of players
	public static final int MIN_NUM_PLAYERS = 2;
	public static final int MAX_NUM_PLAYERS = 4;
	
	// Final variables for starting resources and player positions
	public static final int STARTING_BITCOIN = 500;
	public static final int STARTING_POSITION = 0;
	

	// creation of player list
	public static ArrayList<Player> playerOrder;
	
	
	/**
	 * Method for the main gameplay logic of the game
	 */
	public void startGame() {
		
		boolean gameOver = false;
		
		// display ascii title message and game board layout
		displayTitle();
		
		// arraylist to hold players
		ArrayList<Player> list = createPlayers();
		
		// randomly sorting list of players
		playerOrder = sortPlayers(list);

		do {
			currentPlayer = setCurrentPlayer(playerOrder);
			gameOver = playerTakesTurn(currentPlayer);
			
			if (gameOver == false) {
				
				playerMovement(currentPlayer, Board.movementBoard());
				gameOver = Board.checkZone(currentPlayer);
				 
				if (gameOver == false) {
					
					manageResources(currentPlayer, Board.propertyBoard());
					gameOver = endPlayerTurn(currentPlayer);
					
					if (gameOver == false) {
						currentPlayer = setCurrentPlayer(playerOrder);
						playerOrder = nextPlayer(playerOrder);
					} else {
						completeGame(currentPlayer, playerOrder);
					}

				} else {
					completeGame(currentPlayer, playerOrder);
				}
			} else {
				completeGame(currentPlayer, playerOrder);
			}

		} while (gameOver == false);

	}
	
	public static void removePlayer(Player currentPlayer, ArrayList<Player> players) {
		System.out.println("---------------------------------------------------------");
		System.out.println(currentPlayer.getName() + " has been removed from the game.");
		System.out.println("All of their assets have been tranfered back to NASA");
		System.out.println("---------------------------------------------------------");
		players.remove(currentPlayer);
		// Reseting all of the player's assets to default values
		for (int i = 0; i < Board.PROPERTYBOARD_SIZE; i++) {
			if (Board.propertyBoard.get(i).getOwner() == currentPlayer) {
				Board.propertyBoard.get(i).setOwner(nasa);
				Board.propertyBoard.get(i).setSysOwner(nasa);
				Board.propertyBoard.get(i).setDevelopmentLevel(0);
				Board.propertyBoard.get(i).setCurrentFee(0);
			}
		}
		System.out.println();
		System.out.println("Remaining players:");
		for (Player p : playerOrder) {
			System.out.println(p.getName());
		}
		System.out.println();
	}
 	
	/**
	 * Displays ASCII art title and welcome message for the players with a built in delay
	 */
	public static void displayTitle() {
		
		System.out.println("   _____          __                 .__         .____    .__  __          \r\n"
				+ "  /  _  \\________/  |_  ____   _____ |__| ______ |    |   |__|/  |_  ____  \r\n"
				+ " /  /_\\  \\_  __ \\   __\\/ __ \\ /     \\|  |/  ___/ |    |   |  \\   __\\/ __ \\ \r\n"
				+ "/    |    \\  | \\/|  | \\  ___/|  Y Y  \\  |\\___ \\  |    |___|  ||  | \\  ___/ \r\n"
				+ "\\____|__  /__|   |__|  \\___  >__|_|  /__/____  > |_______ \\__||__|  \\___  >\r\n"
				+ "        \\/                 \\/      \\/        \\/          \\/             \\/ ");
		System.out.println("\t\t  A game by Ryan, Órfhlaith and Caolán.");
		System.out.println();
		delay("Welcome to Artemis Lite!", 50L);
		System.out.println();
		delay("You must work together to return humans to the moon by 2025!", 50L);
		System.out.println();
		delay("Purchase space systems and develop them to launch spec to complete your mission!", 50L);
		System.out.println();
		delay("GOOD LUCK!", 100L);
		System.out.println();
		System.out.println("                        GAME BOARD: ");
		System.out.println();
		delay("  -------------------------------------------------------", 1L);
		delay(" |             |             |             |             |", 1L);
		delay(" |   *******   |   Advanced  |   Upgraded  | Crew Module |", 1L);
		delay(" |    START    |     SLS     |   RL10C-X   |    Space    |", 1L);
		delay(" |   *******   |   Boosters  |   Engines   |   Capsule   |", 1L);
		delay(" |             |             |             |             |", 1L);
		delay(" |-------------------------------------------------------|", 1L);
		delay(" |             |                           |             |", 1L);
		delay(" |   Lunar     |                           |             |", 1L);
		delay(" |   Terrain   |                           |    Solar    |", 1L);
		delay(" |   Vehicle   |                           |    Panels   |", 1L);
		delay(" |             |                           |             |", 1L);
		delay(" |-------------|                           |-------------|", 1L);
		delay(" |             |                           |             |", 1L);
		delay(" |  Habitable  |                           |  Automated  |", 1L);
		delay(" |  Mobility   |                           |   Docking   |", 1L);
		delay(" |  Platform   |                           |   System    |", 1L);
		delay(" |             |                           |             |", 1L);
		delay(" |-------------------------------------------------------|", 1L);
		delay(" |             |             |             |             |", 1L);
		delay(" | Foundational|   HERACLES  |   Starship  |  *********  |", 1L);
		delay(" |    Space    |    Lander   |   Landing   |   NASA HQ   |", 1L);
		delay(" |   Habitat   |             |   System    |  *********  |", 1L);
		delay(" |             |             |             |             |", 1L);
		delay("  -------------------------------------------------------", 1L);
		System.out.println();
	}
	
	/**
	 * Method to create players for the game
	 * @return ArrayList of player objects initialized with starting resources and names specified by the user
	 */
	public static ArrayList<Player> createPlayers() {
		Scanner sc = new Scanner(System.in);
		// prompting the user to specify the amount of players to play the game
		System.out.printf("How many players would like to play? (Please enter a number between %d and %d):\n",
				MIN_NUM_PLAYERS, MAX_NUM_PLAYERS);
		int numberOfPlayers = 0;
		
		do {
			try {
				numberOfPlayers = sc.nextInt();
				
				// checking that user has specified an appropriate number of players
				if (numberOfPlayers < MIN_NUM_PLAYERS || numberOfPlayers > MAX_NUM_PLAYERS) {
					System.out.println("Please enter a number between " + MIN_NUM_PLAYERS + " and " + MAX_NUM_PLAYERS);
					continue;
				}
				break;
			} catch (InputMismatchException exception) {
				System.out.println("Please enter a number between " + MIN_NUM_PLAYERS + " and " + MAX_NUM_PLAYERS);
				sc.nextLine();
			}
		} while (!(numberOfPlayers >= MIN_NUM_PLAYERS) && (numberOfPlayers <= MAX_NUM_PLAYERS));
		
		
		// arraylist to hold the player objects
		ArrayList<Player> players = new ArrayList<Player>();
		
		// ensuring that they arraylist of players is the same capacity as specified by the user 
		players.ensureCapacity(numberOfPlayers);
		
		// array to store player names
		String[] activePlayersArray = new String[numberOfPlayers];
		
		ArrayList<String> activePlayerList = new ArrayList<String>();
		
		// prompting the user for a name for each of the players and storing this in the array 
		for (int i = 0; i < numberOfPlayers; i++) {
			String name = "cao";
			do {
				System.out.println("Please enter name for player " + (i + 1) + ":");
				name = sc.next();
			} while (name.length() <= Player.MIN_NAME_LENGTH || name.length() >= Player.MAX_NAME_LENGTH);
			
			activePlayersArray[i] = name.toUpperCase(); 
		}
		
		// Iterating through the array to make sure that there are no duplicate player names
		for (int i = 0; i < activePlayersArray.length; i++) {
			if (!activePlayerList.contains(activePlayersArray[i].toUpperCase())) {
				activePlayerList.add(activePlayersArray[i]);
			} else {
				do {
					System.err.println("Duplicate name for player " + (i + 1) + ":  " + activePlayersArray[i]
							+ " entered, please enter a different username to continue with the game");
					activePlayersArray[i] = sc.next();
				} while (activePlayerList.contains(activePlayersArray[i]));
			}
		}
		
		// Iterating though the active players array and adding each of the player names as a separate player object within the players arraylist
		for (int i = 0; i < activePlayersArray.length; i++) {
			players.add(new Player(activePlayersArray[i], STARTING_BITCOIN, STARTING_POSITION));
		}
		
		return players;
	}
	
	/**
	 * Method to allow player to traverse the board
	 * @param currentPlayer
	 * @param movementBoard
	 */
	public static void playerMovement(Player currentPlayer, ArrayList<Zone> movementBoard) {
		int rollNumber = currentPlayer.diceRoll();
		// result var stores currentPlayerPosition + rollNumbervalue
		int result = currentPlayer.getPosition() + rollNumber;
		//System.out.println("\nYour current element position is: " + movementBoard.get(currentPlayer.getPosition()).getName());
		
		// checking if a player is on the element, if true, then roll is calculated again
		if (Board.propertyBoard().get(result).isPlayerOnElement() == true) {
			rollNumber = currentPlayer.diceRoll();
			// result var stores currentPlayerPosition + rollNumbervalue
			result = currentPlayer.getPosition() + rollNumber;
		} else {
			// Before player moves to new element, we set current element to vacant so that other players can now land here
			Board.movementBoard().get(currentPlayer.getPosition()).setPlayerOnElement(false);
			
			System.out.println("You rolled " + rollNumber);
			System.out.println();
			// Checking if player has passed START
			if (result > 12) {
				result -= 12;
				// crediting account for passing START
				currentPlayer.setBtc(currentPlayer.getBtc() + 100);
				System.out.println("---------------------------------------------------------");
				System.out.println("You have passed START! \n100 BTC has been credited to your account");
				System.out.println("Your new balance is: " + currentPlayer.getBtc() + " BTC");
				System.out.println("---------------------------------------------------------");
				
				// Setting new player position
				currentPlayer.setPosition(result);
				// Updating player position to let game know that element is occupied by player
				Board.movementBoard.get(result).setPlayerOnElement(true);
				System.out.println("New Position:\t" + movementBoard.get(currentPlayer.getPosition()).getName());
				System.out.println("Space System:\t" + movementBoard.get(currentPlayer.getPosition()).getSystem().toString());
				//System.out.println();
			} else {
				// Setting new player position
				currentPlayer.setPosition(result);
				// Updating player position to let game know that element is occupied by player
				Board.movementBoard.get(result).setPlayerOnElement(true);
				System.out.println("New Position:\t" + movementBoard.get(currentPlayer.getPosition()).getName());
				System.out.println("Space System:\t" + movementBoard.get(currentPlayer.getPosition()).getSystem().toString());
				//System.out.println();
			} 
		}
	}
	
	/**
	 * Displays the randomly sorted list of players for the beginning of the game
	 * @param playerList
	 * @return
	 */
	public static ArrayList<Player> sortPlayers(ArrayList<Player> playerList) {
		
		Collections.shuffle(playerList);
		System.out.println("---------------------------------------------------------");
		System.out.println("Player order has been randomly sorted. Please see below: ");
		System.out.println("---------------------------------------------------------");
		System.out.println();
		
		for (int i = 0; i < playerList.size(); i++) {
			System.out.println("Player " + (i+1) + ": " + playerList.get(i).getName());
			System.out.println();
		}
		return playerList;
	}

	/**
	 * Rotates the arraylist of players so that the next player can be called as currentPlayer and take their turn 
	 * @param playerList
	 * @return
	 */
	public static ArrayList<Player> nextPlayer(ArrayList<Player> playerList) {
		Collections.rotate(playerList, -1);
		return playerList;
	}
	
	/**
	 * Takes an arraylist of randomly sorted players and sets the current player to the first index in the list
	 * @param playerList
	 * @return Player currentPlayer
	 */
	public static Player setCurrentPlayer(ArrayList<Player> playerList) {
		Player currentPlayer = playerList.get(0);
		return currentPlayer;
	}
	
	/**
	 * Method which allows the player to purchase the blueprint for an element when they land on it
	 * @param currentPlayer
	 * @param currentElement
	 */
	public static void buyBlueprint(Player currentPlayer, Element currentElement) {
		System.out.println("Blueprint Cost:\t" + currentElement.getblueprintCost() + " BTC");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		
		// checking that they player has enough BTC to purchase the blueprint
		if (currentPlayer.getBtc() >= currentElement.getblueprintCost()) {
			System.out.println("Do you want to purchase the blueprint for this Element? (y/n)");
			String input = sc.next();
			if (input.equalsIgnoreCase("n")) {
				System.out.println("You have chosen not to purchase the blueprint on this element.");
				
			} else if (input.equalsIgnoreCase("y")) {
				// debiting player's balance by the cost of the blueprint
				currentPlayer.setBtc(currentPlayer.getBtc() - currentElement.getblueprintCost());
				// updating the owner of the element
				currentElement.setOwner(currentPlayer);
				
				//checkIfPlayerOwnsSystem(currentPlayer);
				//2systemOwners(currentPlayer);
				
				// setting the current fee for the element and print to screen purchase details
				currentElement.setCurrentFee(currentElement.getblueprintFee());
				System.out.println();
				System.out.println("Congratulations, your purchase was successful!");
				System.out.println();
				System.out.println("Your new Bitcoin balance is: " + currentPlayer.getBtc() + " BTC");
				System.out.println("---------------------------------------------------------");
				System.out.println("\t    Details of your purchase:");
				System.out.println("---------------------------------------------------------");
			
				currentElement.displaySpaceSystemDetails();
				} 	
		} else {
			System.out.println("You currently don't have enough BitCoin to purchase this element!");
		}
	}
	
	/**
	 * Method for dealing with paying fees when a player lands on an element that is owned by another player
	 * @param currentPlayer
	 * @param currentElement
	 * @return
	 */
	public static boolean payFee(Player currentPlayer, Element currentElement) {
		boolean gameOver = false;

		System.out.println("Fee Due:\t" + currentElement.getCurrentFee() + " BTC");
		System.out.println();
		
		// checking if the player has sufficient resources to pay the fee
		if (currentPlayer.getBtc() >= currentElement.getCurrentFee()) {
			// updating resource balances for both players involved in the transaction
			currentPlayer.setBtc(currentPlayer.getBtc() - currentElement.getCurrentFee());
			currentElement.getOwner().setBtc(currentElement.getOwner().getBtc() + currentElement.getCurrentFee());
			System.out.println("Payment successful...");
			System.out.println("Balance:\t" + currentPlayer.getBtc() + " BTC");
			gameOver = false;
		} else {
			System.out.println("You do not have sufficient funds to pay the fee.\nThe game is now over.");
			gameOver = true;
		}
		return gameOver;
	} 
	
		
	/*
	 * Method that allows the player to manage their resources by developing elements on any of the systems that they own
	 */
	public static void manageResources(Player currentPlayer, ArrayList<Element> propertyBoard) {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		pressEnterKeyToContinue();
		
		// Checking if any systems are owned by the current player and updating system owners
		updateSystemOwners(currentPlayer);
		
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\tMANAGE RESOURCES");
		System.out.println("---------------------------------------------------------");
		System.out.println();
		
		// Checking if player owns any systems, if not then they cannot develop any elements
		if (checkIfPlayerOwnsAnySystems(currentPlayer) == false) {
			System.out.println("You do not own any systems yet.");
		} else {
			
			try {
				int input;
				do {
					// If the player owns any systems, they will be asked which elements within those systems they wish to develop
					System.out.println("Current balance: " + currentPlayer.getBtc() + " BTC");
					System.out.println();
					System.out.println("Please choose which element you would like to develop:");
					System.out.println("Type '0' to stop developing...");
					System.out.println();
					
					
					// prints list of elements available to develop 
					printElementsWherePlayerOwnsSystem(currentPlayer);
					
					
					// asks for user input on which element to develop
					input = sc.nextInt();
					
					// switch case to deal with player input, developing the element of their choice to the next dev phase
					switch (input) {
					case 1:
						developElement(0, currentPlayer);
						System.out.println(Board.propertyBoard().get(0).getName() + " successfully developed!");
						Board.propertyBoard().get(0).displayDevelopmentDetails();
						break;
					case 2:
						developElement(1, currentPlayer);
						System.out.println(Board.propertyBoard().get(1).getName() + " successfully developed!");
						Board.propertyBoard().get(1).displayDevelopmentDetails();
						break;
					case 3:
						developElement(2, currentPlayer);
						System.out.println(Board.propertyBoard().get(2).getName() + " successfully developed!");
						Board.propertyBoard().get(2).displayDevelopmentDetails();
						break;
					case 4:
						developElement(3, currentPlayer);
						System.out.println(Board.propertyBoard().get(3).getName() + " successfully developed!");
						Board.propertyBoard().get(3).displayDevelopmentDetails();
						break;
					case 5:
						developElement(4, currentPlayer);
						System.out.println(Board.propertyBoard().get(4).getName() + " successfully developed!");
						Board.propertyBoard().get(4).displayDevelopmentDetails();
						break;
					case 6:
						developElement(5, currentPlayer);
						System.out.println(Board.propertyBoard().get(5).getName() + " successfully developed!");
						Board.propertyBoard().get(5).displayDevelopmentDetails();
						break;
					case 7:
						developElement(6, currentPlayer);
						System.out.println(Board.propertyBoard().get(6).getName() + " successfully developed!");
						Board.propertyBoard().get(6).displayDevelopmentDetails();
						break;
					case 8:
						developElement(7, currentPlayer);
						System.out.println(Board.propertyBoard().get(7).getName() + " successfully developed!");
						Board.propertyBoard().get(7).displayDevelopmentDetails();
						break;
					case 9:
						developElement(8, currentPlayer);
						System.out.println(Board.propertyBoard().get(8).getName() + " successfully developed!");
						Board.propertyBoard().get(8).displayDevelopmentDetails();
						break;
					case 10:
						developElement(9, currentPlayer);
						System.out.println(Board.propertyBoard().get(9).getName() + " successfully developed!");
						Board.propertyBoard().get(9).displayDevelopmentDetails();
						break;

					default:
						System.out.println("Select an option from the menu or type '0' to quit");
						break;
					}

				} while (input != 0);
			} catch (Exception inputException) {
			}
		}
	}
	
	/**
	 * Returns the enum space system of the element for the current player position
	 * @param currentPlayer
	 * @return enum spacesystem
	 */
	public static eSpaceSystem getSystemOfCurrentElement(Player currentPlayer) {
		int position = currentPlayer.getPosition();
		eSpaceSystem spaceSystem = eSpaceSystem.NEUTRAL;
		spaceSystem = Board.propertyBoard().get(position).getSystem();
		return spaceSystem;
	}
	
	
	/**
	 * Checks if the player owns any systems on the board, used to verify whether they can develop any elements
	 * @param currentPlayer
	 * @return boolean value to indicate whether the player owns any systems
	 */
	private static boolean checkIfPlayerOwnsAnySystems(Player currentPlayer) {
		boolean ownsSystems = false;
		
		for (int i = 0; i < Board.PROPERTYBOARD_SIZE; i++) {
			if (Board.propertyBoard().get(i).getSysOwner() == currentPlayer) {
				ownsSystems = true;
			}
		}
		return ownsSystems;
	}
	
	/**
	 * Finds all the elements where the current player is the system owner, prints the name of the element and the index number to the screen
	 * @param currentPlayer
	 */
	public static void printElementsWherePlayerOwnsSystem(Player currentPlayer) {
		for (int i = 0; i < Board.PROPERTYBOARD_SIZE; i++) {
			if (Board.propertyBoard().get(i).getSysOwner() == currentPlayer) {
				System.out.println((i+1) + " " + Board.propertyBoard().get(i).getName());
			}
		}	
	}
	
	/**
	 * Method checks all of the elements on the board and checks if the current player owns all of the elements
	 * within a system. If they do, it changes the system owner for all the elements in the system to the current player
	 * @param currentPlayer
	 */
	public static void updateSystemOwners(Player currentPlayer) {
		
		// setting system owner of Space Launch System (index 0 and 1)
		if(Board.propertyBoard().get(0).getOwner() == currentPlayer && Board.propertyBoard().get(1).getOwner() == currentPlayer) {
			Board.propertyBoard().get(0).setSysOwner(currentPlayer);
			Board.propertyBoard().get(1).setSysOwner(currentPlayer);
		}
		
		// Setting system owner of Orion (index 2, 3 and 4)
		if(Board.propertyBoard().get(2).getOwner() == currentPlayer && Board.propertyBoard().get(3).getOwner() == currentPlayer && Board.propertyBoard().get(4).getOwner() == currentPlayer) {
			Board.propertyBoard().get(2).setSysOwner(currentPlayer);
			Board.propertyBoard().get(3).setSysOwner(currentPlayer);
			Board.propertyBoard().get(4).setSysOwner(currentPlayer);
		}
		
		// Setting system owner of Landing System (index 5 and 6)
		if(Board.propertyBoard().get(5).getOwner() == currentPlayer && Board.propertyBoard().get(6).getOwner() == currentPlayer) {
			Board.propertyBoard().get(5).setSysOwner(currentPlayer);
			Board.propertyBoard().get(6).setSysOwner(currentPlayer);
		}
		
		// Setting system owner Artemis Base Camp (index 7, 8 and 9)
		if(Board.propertyBoard().get(7).getOwner() == currentPlayer && Board.propertyBoard().get(8).getOwner() == currentPlayer && Board.propertyBoard().get(9).getOwner() == currentPlayer) {
			Board.propertyBoard().get(7).setSysOwner(currentPlayer);
			Board.propertyBoard().get(8).setSysOwner(currentPlayer);
			Board.propertyBoard().get(9).setSysOwner(currentPlayer);
		}
	}
	
	/**
	 * Method increments the development level of the element and deducts player balance
	 * based on the development cost of the relevant dev stage
	 * @param index = the propertyboard index of the element which is being developed
	 * @param currentPlayer
	 */
	public static void developElement(int index, Player currentPlayer) {
		
		// checking player has sufficient resources to develop
		if (currentPlayer.getBtc() < 200) {
			System.out.println("You don't have sufficient resources to develop this element...");
		} else {
			// Incrementing development level by 1
			Board.propertyBoard().get(index).incremenetDevelopmentLevel();

			// Deducting player balance by cost of development
			if(Board.propertyBoard().get(index).getDevelopmentLevel() == 1) {
				// deducting player balance for cost of development 1
				currentPlayer.setBtc(currentPlayer.getBtc() - Board.propertyBoard().get(index).getdevOneCost());
				// setting current fee to dev 1 fee
				Board.propertyBoard().get(index).setCurrentFee(Board.propertyBoard().get(index).getdevOneFee());
			} else if (Board.propertyBoard().get(index).getDevelopmentLevel() == 2) {
				// deducting player balance for cost of development 2
				currentPlayer.setBtc(currentPlayer.getBtc() - Board.propertyBoard().get(index).getdevTwoCost());
				// setting current fee to dev 2 fee
				Board.propertyBoard().get(index).setCurrentFee(Board.propertyBoard().get(index).getdevTwoFee());
			} else if (Board.propertyBoard().get(index).getDevelopmentLevel() == 3) {
				// deducting player balance for cost of development 3
				currentPlayer.setBtc(currentPlayer.getBtc() - Board.propertyBoard().get(index).getdevThreeCost());
				// setting current fee to dev 3 fee
				Board.propertyBoard().get(index).setCurrentFee(Board.propertyBoard().get(index).getDevThreeFee());
			} else {
				System.out.println("This element is already fully developed to launch-spec");
			}
		}
	}
	
	/**
	 * Method that deals with the majority of the players turn, including updating the player's stats and prompting them for input on 
	 * what they would like to do on their turn
	 * @param currentPlayer
	 * @return
	 */
	public static boolean playerTakesTurn(Player currentPlayer) {
		
		// arraylist to hold the elements owned by the player so that these can be displayed during their turn
		ArrayList<String> playerOwnsElements = new ArrayList<>();
		
		// adding player owned elements to the arraylist
		for (int i = 0; i < Board.PROPERTYBOARD_SIZE; i++) {
			if (Board.propertyBoard().get(i).getOwner() == currentPlayer) {
				playerOwnsElements.add(Board.propertyBoard().get(i).getName());
			}
		}
		
		// set to hold the systems that the player owns, using a set to ensure that each system only displays once within the console
		Set<String> playerOwnsSystemsSet = new HashSet<>();
		//ArrayList<String> playerOwnsSystems = new ArrayList<>();
		
		// adding relevant systems to the hashset that the player owns
		for (int i = 0; i < Board.PROPERTYBOARD_SIZE; i++) {
			if (Board.propertyBoard().get(i).getSysOwner() == currentPlayer) {
				playerOwnsSystemsSet.add(Board.propertyBoard().get(i).getSystem().toString());
			}
		}
		
		boolean gameOver = false;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------------------------------------------------");
		System.out.println("\t    " + currentPlayer.getName() + ", it's your turn!");
		System.out.println("---------------------------------------------------------");
		
		System.out.println("Position:\t" + Board.movementBoard().get(currentPlayer.getPosition()).getName());
		System.out.println("BTC Balance:\t" + currentPlayer.getBtc());
		System.out.println("Owned Elements:\t" + playerOwnsElements);
		System.out.println("Owned Systems:\t" + playerOwnsSystemsSet);
				
				
		System.out.println();
		System.out.println("Would you like to continue? (y/n)");
		
		String input = sc.next();
		
		if (input.equalsIgnoreCase("n")) {
			
			System.out.println(currentPlayer.getName() + ", are you sure you want to quit? (y/n)");
			String check = sc.next();
			if (check.equalsIgnoreCase("y")) {
				
				// removing player from the game
				removePlayer(currentPlayer, playerOrder);
				
				// checking if enough remaining players to continue game
				if (playerOrder.size() > 1) {
					// moving to next player turn
					currentPlayer = setCurrentPlayer(playerOrder);
					playerTakesTurn(currentPlayer);
				} else {
					gameOver = true;
					completeGame(currentPlayer, playerOrder);
				}
				
			} else if (check.equalsIgnoreCase("n")) {
				playerTakesTurn(currentPlayer);
			} else {
				System.out.println("Try that again!");
				playerTakesTurn(currentPlayer);
			}
		} else {
			System.out.println("Would you like to continue playing? (y/n)");
			
		}
		return gameOver;
	}
	
	/**
	 * Method for checking if a player would like to continue playing after their turn has ended 
	 * @param currentPlayer
	 * @return boolean gameOver for a player who wants to quit
	 */
	public static boolean endPlayerTurn(Player currentPlayer) {
		// initializing boolean value to false
		boolean gameOver = false;
		Scanner sc = new Scanner(System.in);
		
		// prompting player to continue playing
		System.out.println("\n" + currentPlayer.getName()
				+ " it is the end of your turn... \nWould you like to continue playing the game? (y/n)");
		String input;
		for (;;) {
			input = sc.next();
			// case statement for player who wants to continue to play the game
			if (input.equalsIgnoreCase("y")) {
				gameOver = false;
			} else if (input.equalsIgnoreCase("n")) {
				// case statement for player who wants to leave / end the game
				System.out.println(currentPlayer.getName() + " are you sure you want to quit? (y/n)");
				String quit = sc.next();
				// player confirms they wish to quit
				if (quit.equalsIgnoreCase("y")) {
					System.out.println("End of Game");
					gameOver = true;
				} else if (quit.equalsIgnoreCase("n")) {
					// player does not confirm they wish to quit
					playerTakesTurn(currentPlayer);
				} else {
					System.out.println("Try that again!");
					playerTakesTurn(currentPlayer);
				}
			} else {
				// dealing with other types of input
				System.out.println("Would you like to continue playing? (y/n)");
				continue;
			}
			return gameOver;
		}
	}
	
	/**
	 * Method to display messages to the players when less than 2 players remain in the game
	 * @param currentPlayer last remaining player
	 * @param playerOrder players ordered by BTC balance
	 */
	public static void completeGame(Player currentPlayer, ArrayList<Player> playerOrder) {
		
		if (playerOrder.size() == 1) {
			System.out.println(playerOrder.get(0).getName() + ", looks like you are the last remaining player.\n"
					+ "Congratulations on making it through with a BTC balance of " + playerOrder.get(0).getBtc() + ".");
			System.exit(0);
		} else {
			Collections.sort(playerOrder, new PlayerSort());
			System.out.println("Remaining players sorted by BTC balance:\n");
			for (Player p : playerOrder) {
				System.out.println(p.getName() + ":\t" + p.getBtc() + " BTC");
			}
			if (playerOrder.get(0).getBtc() > playerOrder.get(1).getBtc()) {
				System.out.println("\nCongratulations " + playerOrder.get(0).getName()
						+ " you have won with a remaining balance of " + playerOrder.get(0).getBtc() + "!");
			} else {
				System.out.println("No overall winner as more than one player has the highest remaining balance.");
			}
		}
	}

	/**
	 * Prompts the user to press a key to continue
	 */
	private static void pressEnterKeyToContinue() { 
        System.out.println("Press Enter key to continue...");
        	try {
        		System.in.read();
        	} catch(Exception e) {
        		
        	}  
	 }
	
	/**
	 * Provides a delay between characters when printing text to the screen
	 * @param s
	 * @param delay
	 */
	public static void delay(String s, long delay) {
	    for ( int i= 0; i < s.length(); i++) { 
	          // for i delays individual String characters

	        System.out.print(s.charAt(i));
	        try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //time is in milliseconds
	    }
	    System.out.println(""); // this is the space in between lines
	}
}
