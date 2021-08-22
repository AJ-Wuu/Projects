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
