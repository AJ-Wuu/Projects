/**
 * @author AJWuu
 */

package tetris;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class KeyMoves extends Container implements ActionListener, KeyListener {
	JPanel board;
	Timer timer = new Timer(1, this);
	int velocity, posX, currVelo;
	
	public KeyMoves(JPanel board, int squareSize, int posX) {
		this.board = board;
		this.velocity = squareSize;
		this.posX = posX;
		
		timer.start();
		board.addKeyListener(this);
		board.setFocusable(true); //allow to use a KeyListener
		board.setFocusTraversalKeysEnabled(false); //avoid bizarre actions, like "Tab" key
	}
	
	public void actionPerformed(ActionEvent e) {
		//repaint();
		posX += currVelo;
	}
	
	public void left() {
		currVelo = -velocity;
	}
	
	public void right() {
		currVelo = velocity;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); //each key has its own unique code
		if (code == KeyEvent.VK_LEFT) {
			left();
		}
		else if (code == KeyEvent.VK_RIGHT) {
			right();
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
