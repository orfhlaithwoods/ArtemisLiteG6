package artemisLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameAdmin {

	public static final int MIN_NUM_PLAYERS = 2;
	public static final int MAX_NUM_PLAYERS = 4;
	public static final int STARTING_BITCOIN = 500;
	public static final int STARTING_XP = 0;
	public static final int STARTING_POSITION = 0;
	public static Player currentPlayer = new Player();
	public static Player nasa = new Player();
	public static eSpaceSystem eSpaceSystem;
	
	
	

	// creation of player list
	public static ArrayList<Player> playerOrder;

	public void gamePlay() {

		boolean gameOver = false;

		ArrayList<Player> list = createPlayers();

		playerOrder = displayPlayerList(list);

		do {
			currentPlayer = setCurrentPlayer(playerOrder);

			gameOver = quitGameTakeTurn(currentPlayer);

			if (!gameOver) {
				playerMovement(currentPlayer, Board.movementBoard());
				gameOver = Board.checkArea(currentPlayer);

				if (!gameOver) {
					manageResources(currentPlayer, Board.propertyBoard());
					gameOver = quitGameEndTurn(currentPlayer);

					if (!gameOver) {
						playerOrder = nextPlayer(playerOrder);
						currentPlayer = setCurrentPlayer(playerOrder);

					} else {
						missionOverStatus(currentPlayer, playerOrder);
					}

				} else {
					missionOverStatus(currentPlayer, playerOrder);
				}
			} else {
				missionOverStatus(currentPlayer, playerOrder);
			}

		} while (!gameOver);

	}

	public static void playerMovement(Player currentPlayer, ArrayList<Area> movementBoard) {
		int rollNumber = currentPlayer.takeTurn();
		// result var stores currentPlayerPosition + rollNumbervalue
		int result = currentPlayer.getPosition() + rollNumber;
		//System.out.println("\nYour current element position is: " + movementBoard.get(currentPlayer.getPosition()).getName());

		System.out.println("You rolled " + rollNumber);
		System.out.println();
		// Checking if player has passed START
		if (result > 12) {
			result -= 12;
			currentPlayer.setBtc(currentPlayer.getBtc() + 100);
			System.out.println("---------------------------------------------------------");
			System.out.println("You have passed START! \n100 BTC has been credited to your account");
			System.out.println("Your new balance is: " + currentPlayer.getBtc() + " BTC");
			System.out.println("---------------------------------------------------------");
			
			currentPlayer.setPosition(result);
			System.out.println("New Position:\t" + movementBoard.get(currentPlayer.getPosition()).getName());
			System.out.println("Space System:\t" + movementBoard.get(currentPlayer.getPosition()).getSystem().toString());
			//System.out.println();
		} else {
			currentPlayer.setPosition(result);
			System.out.println("New Position:\t" + movementBoard.get(currentPlayer.getPosition()).getName());
			System.out.println("Space System:\t" + movementBoard.get(currentPlayer.getPosition()).getSystem().toString());
			//System.out.println();
		}
		
	}

	public static ArrayList<Player> createPlayers() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("How many players would like to play? (Please enter a number between %d and %d inclusive)\n",
				MIN_NUM_PLAYERS, MAX_NUM_PLAYERS);
		int numberOfPlayers;
		for (;;) {
			try {
				numberOfPlayers = scanner.nextInt();

				if (numberOfPlayers < MIN_NUM_PLAYERS || numberOfPlayers > MAX_NUM_PLAYERS) {
					System.out.println("Please enter a number between " + MIN_NUM_PLAYERS + " and " + MAX_NUM_PLAYERS);
					continue;
				}
				break;
			} catch (InputMismatchException exception) {
				System.out.println("Please enter a number between " + MIN_NUM_PLAYERS + " and " + MAX_NUM_PLAYERS);
				scanner.nextLine();
			}
		}

		ArrayList<Player> playersArrayList = new ArrayList<Player>();
		playersArrayList.ensureCapacity(numberOfPlayers);
		String[] activePlayersArray = new String[numberOfPlayers];
		ArrayList<String> activePlayerList = new ArrayList<String>();
		for (int loop = 0; loop < numberOfPlayers; loop++) {
			System.out.println("Please enter the player's username " + (loop + 1));
			activePlayersArray[loop] = scanner.next().toUpperCase();
		}

		for (int i = 0; i < activePlayersArray.length; i++) {
			if (!activePlayerList.contains(activePlayersArray[i].toUpperCase())) {
				activePlayerList.add(activePlayersArray[i]);
			} else {
				do {
					System.err.println("Duplicate username for player " + (i + 1) + ":  " + activePlayersArray[i]
							+ " entered, please enter a different username to continue with the game");
					activePlayersArray[i] = scanner.next();
				} while (activePlayerList.contains(activePlayersArray[i]));
			}
		}
		for (int i = 0; i < activePlayersArray.length; i++) {
			playersArrayList.add(new Player(activePlayersArray[i], STARTING_BITCOIN, STARTING_POSITION, STARTING_XP));
		}
		ArrayList<Player> players = playersArrayList;
		return players;
	}

	public static ArrayList<Player> displayPlayerList(ArrayList<Player> playerList) {
		Collections.shuffle(playerList);
		System.out.println("---------------------------------------------------------");
		System.out.println("Player order has been randomly sorted. Please see below: ");
		System.out.println("---------------------------------------------------------");
		System.out.println();
		for (int loop = 0; loop < playerList.size(); loop++) {
			System.out.println("Player " + (loop+1) + ": " + playerList.get(loop).getName());
			System.out.println();
		}
		return playerList;
	}

	public static ArrayList<Player> nextPlayer(ArrayList<Player> playerList) {
		Collections.rotate(playerList, -1);
		return playerList;
	}

	public static Player setCurrentPlayer(ArrayList<Player> playerList) {
		Player currentPlayer = playerList.get(0);
		return currentPlayer;
	}

	public static boolean payFee(Player currentPlayer, Element currentElement) {
		boolean gameOver = false;
		System.out.println(currentPlayer.getName() + " your BTC balance is: " + currentPlayer.getBtc() + "BTC");
		System.out.println(
				"You have to pay: " + currentElement.getCurrentFee() + " BTC to " + currentElement.getOwner().getName());

		if (currentPlayer.getBtc() >= currentElement.getCurrentFee()) {
			currentPlayer.setBtc(currentPlayer.getBtc() - currentElement.getCurrentFee());
			currentElement.getOwner().setBtc(currentElement.getOwner().getBtc() + currentElement.getCurrentFee());
			System.out.println(currentPlayer.getName() + " your new BTC balance is: " + currentPlayer.getBtc() + "BTC");
			gameOver = false;
		} else {
			System.out.println("You do not have sufficient funds.\nThe game is now over.");
			gameOver = true;
		}
		return gameOver;
	}

	public static void buyBlueprint(Player currentPlayer, Element currentElement) {
		System.out.println("Blueprint Cost:\t" + currentElement.getblueprintCost() + " BTC");
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		
		if (currentPlayer.getBtc() >= currentElement.getblueprintCost()) {
			System.out.println("Do you want to purchase the blueprint for this Element? (y/n)");
			String input = scanner.next();
			if (input.equalsIgnoreCase("n")) {
				System.out.println("You have chosen not to purchase the blueprint on this element.");
				
			} else if (input.equalsIgnoreCase("y")) {
					currentPlayer.setBtc(currentPlayer.getBtc() - currentElement.getblueprintCost());
					currentElement.setOwner(currentPlayer);
					
					//checkIfPlayerOwnsSystem(currentPlayer);
					
					//2systemOwners(currentPlayer);
					
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
			
	

	public static void manageResources(Player currentPlayer, ArrayList<Element> propertyBoard) {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		pressAnyKeyToContinue();
		
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
			String input = null;
			do {
				
				// If the player owns any systems, they will be asked which elements within those systems they wish to develop
				System.out.println("Current balance: " + currentPlayer.getBtc() + " BTC");
				System.out.println();
				System.out.println("Please choose which element you would like to develop:");
				
				// prints list of elements available to develop 
				printElementsWherePlayerOwnsSystem(currentPlayer);
				
				try {
					// asks for user input on which element to develop
					Scanner sc = new Scanner(System.in);
					input = sc.nextLine();
					
					// develops the relevant element based on user input
					developElement((Integer.parseInt(input) -1), currentPlayer);
					System.out.println("Type 'done' to finish developing elements");
					
				} catch (Exception e) {
					System.out.println("Whoops, something went wrong. Make sure you select an option or type 'done'");
				}
	
			} while (input != "done");
		}
	}
	

	public static boolean quitGameTakeTurn(Player currentPlayer) {
		
		
		ArrayList<String> playerOwns = new ArrayList<>();
		
		for (int i = 0; i < Board.PROPERTYBOARD_SIZE; i++) {
			if (Board.propertyBoard().get(i).getOwner() == currentPlayer) {
				playerOwns.add(Board.propertyBoard().get(i).getName());
			}
		}
		
		
		boolean gameOver = false;
		Scanner playerCheckerStart = new Scanner(System.in);
		
		System.out.println("---------------------------------------------------------");
		System.out.println("\t    " + currentPlayer.getName() + ", it's your turn!");
		System.out.println("---------------------------------------------------------");
		
		System.out.println("Position:\t" + Board.movementBoard().get(currentPlayer.getPosition()).getName());
		System.out.println("BTC Balance:\t" + currentPlayer.getBtc());
		System.out.println("Owned Elements:\t" + playerOwns);
				
				
		System.out.println();
		System.out.println("Would you like to continue? (y/n)");
		
		String input;
		for (;;) {
			input = playerCheckerStart.next();
			if (input.equalsIgnoreCase("y")) {
				System.out.println();
			
				
			} else if (input.equalsIgnoreCase("n")) {
				System.out.println(currentPlayer.getName() + ", are you sure you want to quit? (y/n)");
				String check = playerCheckerStart.next();
				if (check.equalsIgnoreCase("y")) {
					System.err.println("Game Over...  Mission failed!!");
					gameOver = true;
				} else if (check.equalsIgnoreCase("n")) {
					quitGameTakeTurn(currentPlayer);
				} else {
					System.out.println("Invalid input!");
					quitGameTakeTurn(currentPlayer);
				}
			} else {
				System.out.println("Would you like to continue? (Please choose y/n only)");
				continue;
			}
			return gameOver;
		}
		
	}

	public static boolean quitGameEndTurn(Player currentPlayer) {
		boolean gameOver = false;
		Scanner playerCheckerEnd = new Scanner(System.in);
		System.out.println("\n" + currentPlayer.getName()
				+ " it is the end of your turn... \nWould you like to continue playing the game? (y/n)");
		String input;
		for (;;) {
			input = playerCheckerEnd.next();
			if (input.equalsIgnoreCase("y")) {
				gameOver = false;
			} else if (input.equalsIgnoreCase("n")) {
				System.out.println(currentPlayer.getName() + " are you sure you want to quit? (y/n)");
				String check = playerCheckerEnd.next();
				if (check.equalsIgnoreCase("y")) {
					System.out.println("End of Game");
					gameOver = true;
				} else if (check.equalsIgnoreCase("n")) {
					quitGameTakeTurn(currentPlayer);
				} else {
					System.err.println("Invalid input!");
					quitGameTakeTurn(currentPlayer);
				}
			} else {
				System.out.println("Would you like to continue? (Please choose y/n only)");
				continue;
			}
			return gameOver;
		}
	}

	public static void missionOverStatus(Player currentPlayer, ArrayList<Player> playerOrder) {
		playerOrder.remove(0);
		if (playerOrder.size() == 1) {
			System.out.println("Congratulations " + playerOrder.get(0).getName()
					+ " you have won with a remaining balance of " + playerOrder.get(0).getBtc());
		} else {
			Collections.sort(playerOrder, new PlayerSort());
			System.out.println("Remaining players sorted by XP:\n");
			for (Player p : playerOrder) {
				System.out.println(p.getName() + " has " + p.getBtc());
			}
			if (playerOrder.get(0).getBtc() > playerOrder.get(1).getBtc()) {
				System.out.println("\nCongratulations " + playerOrder.get(0).getName()
						+ " you have won with a remaining balance of " + playerOrder.get(0).getBtc());
			} else {
				System.out.println("No overall winner as more than one player has the highest remaining balance.");
			}
		}
	}
	
	
	
	public static eSpaceSystem getSystemOfCurrentElement(Player currentPlayer) {
		int position = currentPlayer.getPosition();
		eSpaceSystem spaceSystem = eSpaceSystem.NEUTRAL;
		
		
		spaceSystem = Board.propertyBoard().get(position).getSystem();
		
		
		return spaceSystem;
	}
	
	
	
	private static boolean checkIfPlayerOwnsAnySystems(Player currentPlayer) {
		boolean ownsSystems = false;
		
		for (Element e : Board.propertyBoard()) {
			if (e.getSysOwner() == currentPlayer) {
				ownsSystems = true;
			} else {
				ownsSystems = false;
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
		
		// Incrementing development level by 1
		Board.propertyBoard().get(index).incremenetDevelopmentLevel();
		
		// Deducting player balance by cost of development
		if(Board.propertyBoard().get(index).getDevelopmentLevel() == 1) {
			// deducting player balance for cost of development 1
			currentPlayer.setBtc(currentPlayer.getBtc() - Board.propertyBoard().get(index).getdevOneCost());
		} else if (Board.propertyBoard().get(index).getDevelopmentLevel() == 2) {
			// deducting player balance for cost of development 2
			currentPlayer.setBtc(currentPlayer.getBtc() - Board.propertyBoard().get(index).getdevTwoCost());
		} else {
			// deducting player balance for cost of development 3
			currentPlayer.setBtc(currentPlayer.getBtc() - Board.propertyBoard().get(index).getdevThreeCost());
		}
	}

	/**
	 * Prompts the user to press a key to continue
	 */
	private static void pressAnyKeyToContinue() { 
        System.out.println("Press Enter key to continue...");
        	try {
        		System.in.read();
        	} catch(Exception e) {
        		
        	}  
	 }
}
