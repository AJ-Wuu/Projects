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
				FrameAutoMode game = new FrameAutoMode(scan);
			});
		}
		else {
			EventQueue.invokeLater(() -> {
				@SuppressWarnings("unused")
				FrameManualMode game = new FrameManualMode(scan);
			});
		}
	}

}
