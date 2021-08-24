/**
 * @author AJWuu
 */

package schedule;

/*
 * MVC：Model-View-Control
 * Model is to preserve and maintain data, offer interface for the data change from outside, and call View to update
 * View is to get data from Model and draw a new view accordingly
 * Control is to get input from the user and adjust the data according to the input
 * Note that Control does not directly interact with View
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Schedule {
	public static void main(String[] args) {
		//Model--> table--ScheduleData
		//JTable--> View & Control (Combination, commonly used to get the user input from a GUI)
		JFrame frame = new JFrame();
		JTable table = new JTable(new ScheduleData());
		JScrollPane pane = new JScrollPane(table);
		frame.add(pane);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
