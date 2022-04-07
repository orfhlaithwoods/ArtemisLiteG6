package artemisLite;
import java.util.Comparator;

	
	// imports the Comparator class from java util API
	
	/**
	 * PlayerSort class: at the end of the game, the players are sorted in
	 * descending order based on their final balance
	 *
	 */
	public class PlayerSort implements Comparator<Player> {

		/**
		 * compare method
		 */
		@Override
		public int compare(Player o1, Player o2) {

			return o2.getBtc() - o1.getBtc();

		} // end of comparator method

	} // end of SortPlayers class


