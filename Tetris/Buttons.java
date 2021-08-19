package tetris;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Buttons extends Container implements ActionListener {
	boolean isPaused = false;
	boolean isRestart = true;
	public static JButton startButton, pauseButton;
	public int buttonHeight = 30;

	public Buttons(JPanel board, JLabel status, int boardWidth, int boardHeight) {		
		//define
		startButton = new JButton("New Game");
		startButton.addActionListener(this);
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);


		//locate
		startButton.setBounds(0, 0, boardWidth/2-1, buttonHeight);
		pauseButton.setBounds(boardWidth/2+1, 0, boardWidth/2-1, buttonHeight);

		//act
		//There should be no loop in the button action
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText("Let's Go");
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

		//add to board
		//When using layouts, it would be like board.add(startButton, BorderLayout.NORTH);
		//However, this layout being used should be the same as the one declared in board, otherwise, it won't work
		board.add(startButton);
		board.add(pauseButton);

		pauseButton.setEnabled(false); //make pause disabled before the game starts, and enable it after clicking start
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Buttons.startButton) {
			isRestart = !isRestart;
			if (isRestart) {
				pauseButton.setEnabled(false);
			}
			else {
				pauseButton.setEnabled(true);
			}
			Board.start();
		}
		if(e.getSource() == Buttons.pauseButton) {
			Board.pause();
		}
	}
}
