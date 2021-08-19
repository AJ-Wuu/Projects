package tetris;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Tiles extends Thread{
	
    private int size, width, height, rows, cols;
    private Graphics g;
    public static JPanel board;
    public static int numLinesRemoved;

    public Tiles(int squareSize, int boardWidth, int boardHeight, int numRows, int numCols, JPanel panel) {
        this.size = squareSize;
        this.width = boardWidth;
        this.height = boardHeight;
        this.rows = numRows;
        this.cols = numCols;
        board = panel;
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(0, 50, width, 50);
    }
    
    public static boolean keepGoing() {
    	numLinesRemoved = 0;
    	while (checkIfContinue()) {
			System.out.println("Running");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	return true;
    }
    
    public static void dropTile() {
    	
    }
    
    public static void removeFullLine() {
    	numLinesRemoved++;
    }
    
    public static boolean checkIfContinue() {
		return true;
	}
    
}
