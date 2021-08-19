/**
 * @author AJWuu
 */

package tetris;

import java.awt.EventQueue;

public class Driver {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			@SuppressWarnings("unused")
			Frame game = new Frame();
		});
	}

}
