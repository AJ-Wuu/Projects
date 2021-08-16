/**
 * @author modified by AJWuu
 */

package blackjack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Driver {

	public static void main(String[] args) throws Exception {
		PlayGame game = new PlayGame();
		game.initializeGame();
		do {
			game.shuffle();
			game.getBets();
			game.dealCards();
			game.printStatus();
			game.checkBlackjack();
			game.hitOrStand();
			game.dealerPlays();
			game.settleBets();
			game.printMoney();
			game.clearHands();
		} while (game.playAgain());

		try {
			FileOutputStream outObjectFile = new FileOutputStream("objectOut", false);
			ObjectOutputStream outObjectStream = new ObjectOutputStream(outObjectFile);
			outObjectStream.writeObject(game);
			outObjectStream.flush();
			outObjectStream.close();
		} catch(FileNotFoundException fnfException) {
			System.out.println("No file");
		} catch(IOException ioException) {	
			System.out.println("bad IO");
		}
		
		try {
			FileInputStream inObjectFile = new FileInputStream("objectOut");
			ObjectInputStream inObjectStream = new ObjectInputStream(inObjectFile);
			Card myNewCard = (Card)inObjectStream.readObject();
			System.out.println(myNewCard);
			inObjectStream.close();
		} catch(FileNotFoundException fnfException) {
			System.out.println("No File");
		} catch(IOException ioException) {	
			System.out.println("IO no good");
		} catch(ClassNotFoundException cnfException) {	
			System.out.println("This is not a Card.");
		}
	}

}
