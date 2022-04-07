package artemisLite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * PlayGame class
 * 
 * @author 
 *
 */
public class PlayGame {

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// instantiates the GameManager class
		GameAdmin game = new GameAdmin();
		
		// invokes the playGame method
		game.startGame();
		
		try{
			
			PrintStream gameOutput = new PrintStream(new File ("C:\\Users\\Órfhlaith Woods\\eclipse-workspace2\\ArtemisLiteV3 (2).zip_expanded\\ArtemisLiteV3\\gameOutput.txt"));
			System.setOut(gameOutput);
			gameOutput.print(game);
			
			
} catch (FileNotFoundException e) { System.out.println(e);}
	} // end of main method

} // end of PlayGame class
