/**
 * @author AJWuu
 */

package tetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame {
	//By Composition -> Better & More Secure than inheritance (public class Driver extends JFrame)

	public JFrame frame;
	public JLabel status;

    public Frame() {
    	frame = new JFrame();
    	status = new JLabel("Come and Play Tetris!");
        initializeUI();
    }

    private void initializeUI() {
        this.frame.setTitle("Tetris");
        this.startUI();
        this.frame.add(status, BorderLayout.SOUTH);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setVisible(true);
    }
    
    public void startUI() {
    	@SuppressWarnings("unused")
		Board panel = new Board(this.status);
    	this.frame.add(Board.board, BorderLayout.CENTER);
    }
    
}
