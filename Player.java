package artemisLite;

import java.util.Random;

public class Player {

	/**
	 * instance vars
	 */

	private String name;
	private int XP;
	private int btc;
	private int position;

	/**
	 * character length of name string displayed as min & max constants
	 */
	public static final int MIN_NAME_LENGTH = 2;
	public static final int MAX_NAME_LENGTH = 20;
	public static final int MIN_SYSTEM_BOUNDARY = 0;
	public static final int MAX_SYSTEM_BOUNDARY = 12;

	/**
	 * Default constructor for player
	 */
	public Player() {

	}
	
	/*
	 * constructor for nasa player, with name that can be compared to check system owners 
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Constructor with arguments
	 *
	 * @param name
	 * @param knowledge
	 * @param tools
	 * @param people
	 * @param position
	 * @param XP
	 * @param position 
	 */

	public Player(String name, int btc, int XP, int position) {
		super();
		this.name = name;
		this.XP = XP;
		this.setBtc(btc);
		this.position = position;
	}

	/**
	 * Method for getName
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method of setting player name
	 *
	 * @param name the name to set
	 */
	public void setName(String name) throws IllegalArgumentException {

		if (name.length() >= MIN_NAME_LENGTH && name.length() <= MAX_NAME_LENGTH) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Player Name Invalid. Please Enter Correct Amount Of Characters");
		}
	}

	/**
	 * Method for getXP
	 *
	 * @return the xP
	 */
	public int getXP() {
		return XP;
	}

	/**
	 * Method for setting the player XP
	 *
	 * @param sets the XP for the player - if below 0 an exception is thrown
	 */
	public void setXP(int XP) throws IllegalArgumentException {
		if (XP >= 0) {
			this.XP = XP;
		} else {
			throw new IllegalArgumentException("Invalid XP Given");
		}
	}

	/**
	 * Method for getPosition
	 *
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * sets the player position if the position is outside the min and max area
	 * boundary values an exception is thrown
	 *
	 * @param position the position to set
	 */
	public void setPosition(int position) throws IllegalArgumentException {
		if (position > Area.MIN_SYSTEM_BOUNDARY && position <= Area.MAX_SYSTEM_BOUNDARY) {
			this.position = position;
		} else {
			throw new IllegalArgumentException("Invalid Position: " + position);
		}

	}

	/**
	 * Method for getBitcoin(BTC)
	 *
	 * @return the btc
	 */
	public int getBtc() {
		return btc;
	}

	/**
	 * Method for setting the player BTC
	 *
	 * @param sets the BTC for the player - if below 0 an exception is thrown
	 */
	public void setBtc(int btc) {
		if (btc >= 0) {
			this.btc = btc;
		} else {
			throw new IllegalArgumentException("Invalid BTC Given");
		}
	}

	/**
	 * takeTurn method - this generates the random roll of the dice for each player
	 *
	 */
	public int takeTurn() {

		Random roll = new Random();
		int takeTurn = roll.nextInt(10) + 2;

		return takeTurn;
	}

} // end of player class