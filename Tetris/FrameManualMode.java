/**
 * @author AJWuu
 */

package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FrameManualMode extends JFrame {
	//By Inheritance -> public class Frame extends JFrame {}
	//By Composition -> public class Frame { JFrame frame; } -> Better & More Secure

	public JLabel status;
	public int Width = 400, Height = 590, labelSize = 20, squareSize = 20, buttonSize = 30;
	static FrameManualMode frame;
	static BoardManualMode board;

	@SuppressWarnings("unused")
	public FrameManualMode(Scanner scan) {
		instructions(scan);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(Width, Height));
		this.setTitle("Tetris");
		this.setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		//1. change the Layout in the parenthesis (to BorderLayout, for example) for better arranging the place of buttons
		//   Note that the locations would be strictly aligned to the layout defaults, and setBounds() would not work
		//2. change the layout to null will make the panel in the "Absolute Layout",
		//   where it's able to set the position of the JLabel and JButton with setBounds()    

		status = new JLabel("Come and Play Tetris!");
		status.setSize(new Dimension(Width, labelSize));
		status.setForeground(Color.darkGray); //change the word color
		status.setOpaque(true); //a component must be opaque for its background do be effective, a JLabel's default is false
		status.setBackground(Color.lightGray); //change the label background
		status.setBounds(0, Height-labelSize*3, Width, labelSize); //WHY 3???
		frame = this;
		board = new BoardManualMode(this);
		ButtonsManualMode buttons = new ButtonsManualMode(this, status, board, Width, Height); //there is frame.add() within Buttons() and KeyMoves()

		this.add(status);
		this.setVisible(true);
	}

	private void instructions(Scanner scan) {
		System.out.println("Welcome to the Tetris Game!!!");
		System.out.println("Here are some instructions ->");
		System.out.println("	1. Buttons: Press \"New Game\" to start a new game; ");
		System.out.println("	            Press \"Pause\" to temperorily stop the game, and Press \"Resume\" later to restart from the previous place.");
		System.out.println("	2. Arrows: Only left and right are functionable. ");
		System.out.println("	           If simply pressing an arrow doesn't work, please press Tab + arrow first and then arrow (only) is good to go.");
		System.out.println("	3. The game will automatically stop if any block piles above the black line.");
		System.out.println("Okay with this? (Y)es or (N)o");
		String temp = scan.next();
		while (true) {
			if (temp.equals("Y") || temp.equals("y") || temp.equals("Yes") || temp.equals("yes") || temp.equals("YES")) {
				System.out.println("Great! Have Fun :D");
				break;
			}
			else {
				System.out.println("Oh... Let's try it again, shall we?");
				temp = scan.next();
			}
		}
	}

	public static void stop() {
		ButtonsManualMode.pauseButton.setEnabled(false);
		try {
			Thread.sleep(5);
		} catch (InterruptedException except) {
			except.printStackTrace();
		}
	}

}
