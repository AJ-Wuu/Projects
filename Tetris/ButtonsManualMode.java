/**
 * @author AJWuu
 */

package tetris;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ButtonsManualMode extends Container implements ActionListener {
	boolean isPaused = false;
	boolean isRestart = true;
	private FrameManualMode frame;
	private BoardManualMode board;
	public static JButton startButton, pauseButton;
	public static int buttonHeight = 30;

	public ButtonsManualMode(FrameManualMode frame, JLabel status, BoardManualMode board, int Width, int Height) {	
		this.frame = frame;
		this.board = board;
		
		//define
		startButton = new JButton("New Game");
		startButton.addActionListener(this);
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);


		//locate
		startButton.setBounds(0, 0, Width/2-10, buttonHeight);
		pauseButton.setBounds(Width/2+1, 0, Width/2-10, buttonHeight);

		//act
		//There should be no loop in the button action
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isRestart) {
					status.setText("Let's Go");
				}
				else {
					status.setText("Try Again");
				}
				pauseButton.setText("Pause");
			}
		});
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPaused = !isPaused; //change false to true, true to false
				if (isPaused) {
					status.setText("Pausing");
					pauseButton.setText("Resume");
				}
				else {
					status.setText("Keep Going");
					pauseButton.setText("Pause");
				}
			}
		});

		//add to frame
		//When using layouts, it would be like frame.add(startButton, BorderLayout.NORTH);
		//However, this layout being used should be the same as the one declared in frame, otherwise, it won't work
		frame.add(startButton);
		frame.add(pauseButton);

		pauseButton.setEnabled(false); //make pause disabled before the game starts, and enable it after clicking start
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ButtonsManualMode.startButton) {
			isRestart = !isRestart;
			if (isRestart) {
				pauseButton.setEnabled(false);
				frame.remove(board);
			}
			else {
				pauseButton.setEnabled(true);
				start();
			}
		}
		if(e.getSource() == ButtonsManualMode.pauseButton) {
			if (isPaused) {
				try {
					board.wait();
//					Thread.sleep(3000);
				} catch (InterruptedException e2) {
					// TODO Manual-generated catch block
					e2.printStackTrace();
				}
			}
			else { 
				board.notify();
			}
		}
	}
	
	private void start() {
		board = new BoardManualMode(frame);
		frame.add(board);
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
}
