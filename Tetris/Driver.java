/**
 * @author AJWuu
 */

package tetris;

import java.awt.EventQueue;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		System.out.println("(A)utoMode or (M)anualMode?");
		Scanner scan = new Scanner(System.in); //there cannot be two Scanner open together
		String str = scan.next();
		if (str.equals("A") || str.equals("a") || str.equals("AutoMode") || str.equals("automode")) {
			EventQueue.invokeLater(() -> {
				//Swing is basically thread unsafe. i.e., all interaction with that API needs to be performed on a single thread (the EDT).
				//If you need to do GUI updates from another thread (timer thread, networking thread, ...), 
				//you need to use methods like SwingUtilities.invokeLater, SwingUtilities.invokeAndWait, ...
				
				//If the program is not on the EDT and needs to do GUI updates 
				//(like updating the GUI from some timer thread, or from some network thread, etc.), 
				//the actionPerformed methods is to schedule the update to be performed by the EDT.
				
				
				
				@SuppressWarnings("unused")
				Frame game = new Frame(scan, 0);
				//First, There is absolutely no need to close the System.in stream.
				//	Other parts of the program may be using it, and you don't want to interfere with their normal operation.
				//Second, there's no benefit to creating more than one Scanner object.
				//	It's simply reading input from a stream, and having more than one reference isn't necessary or beneficial to your operations.
			});
		}
		else {
			EventQueue.invokeLater(() -> {
				@SuppressWarnings("unused")
				Frame game = new Frame(scan, 1);
			});
		}
	}

}
