/**
 * @author AJWuu
 */

package tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener, KeyListener {

	private static int squareSize, boardWidth, boardHeight, squareNumber, charRow, charCol;
	private static Frame frame;
	private JLabel status;
	private static boolean isNewRound = true, isFinished = false;

	Timer timer = new Timer(5, this);
	int x, y, velX = 0, velY = 1, xLeft = 0, xRight = 0, yDown = 0; //xLeft is always LE 0
	final int xInit = boardWidth/2 - squareSize, yInit = 2; //the initial (x, y) where a new shape is automatically placed
	int[] xPoints, yPoints;
	ShapeCharWithColor shape, newShape;
	int degrees;
	char[] currShapeArray;
	Area shapeG2 = new Area();
	ArrayList<Area> finalShapeG2 = new ArrayList<Area>(); //every popped up area (NOTE that area carries the coordinates itself)
	ArrayList<Color> finalShapeColor = new ArrayList<Color>(); //the color of each corresponding area
	int[] location;
	Hashtable<Integer, Integer> heightsInShape = new Hashtable<Integer, Integer>(); //the heights of each square (relative to the top-left one) in a shape as <x, height at x>
	Hashtable<Integer, Integer> heightsInShapeWithBlank = new Hashtable<Integer, Integer>(); //the heights of each square (relative to the top-left one) as <x, y_max>
	boolean[][] squarePiled; //the height of squares piled on each square of the board
	int[] yPainted; //the largest y of unpainted square for each x
	int bump; //the absolute x_index of the square which meets the criteria that cause it to stop moving
	int count = 0; //the number of full lines removed

	public Board(Frame jframe) {
		frame = jframe;
		status = Frame.status;
		boardWidth = frame.Width;
		boardHeight = frame.Height - 3*frame.labelSize - frame.buttonSize;
		squareSize = frame.squareSize;
		squareNumber = PieceShapeInChar.pieceNumber;
		charRow = PieceShapeInChar.pieceWidth;
		charCol = PieceShapeInChar.pieceHeight;

		x = xInit;
		y = yInit;
		xPoints = new int[4 * squareNumber];
		yPoints = new int[4 * squareNumber];
		location = new int[2 * squareNumber]; //store relative indexes (x,y) of the current shape in a 4x4 matrix
		squarePiled = new boolean[boardWidth/squareSize][boardHeight/squareSize];
		yPainted = new int[boardWidth/squareSize];
		Arrays.fill(yPainted, boardHeight/squareSize);

		this.setSize(new Dimension(boardWidth, boardHeight));
		this.setBounds(0, jframe.buttonSize, boardWidth, boardHeight);

		timer.start();
		this.addKeyListener(this);
		this.setFocusable(true); //allow to use a KeyListener
		//Focus request will only be possibly granted after components have been rendered, so neither pack() nor setVisible(true) should be called before this
		//TAB controls the switch between buttons and KeyListener (in other words, changes the "focus" of the program)
		//To use the KeyListener, we need to press TAB & arrow together
		this.setFocusTraversalKeysEnabled(false); //avoid bizarre actions, like "Tab" key
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		//draw existing shapes
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(0, squareSize*squareNumber, boardWidth, squareSize*squareNumber);
		for (int i=0; i<finalShapeG2.size(); i++) {
			g2.setColor(finalShapeColor.get(i));
			g2.fill(finalShapeG2.get(i)); //paint every existing shapes
		}

		//go with new shape
		if (isNewRound) {
			if (shape != null) {
				//check every movement
				finalShapeG2.add(shapeG2);
				finalShapeColor.add(shape.getColor()); //DO NOT use g2.getColor(), or it will constantly be black
				checkIfStop();
				if (!isFinished) {
					piledUP();
				}
				else {
					return ;
				}
			}
			x = xInit; //Go back to the original settings
			y = yInit;
			shape = null;
			newShape = PieceShapeInChar.getRandomShape(); //get next shape and its rotation degrees
			degrees = PieceShapeInChar.getRandomDegrees();
		}
		shape = newShape;
		shapeG2 = buildShape(shape, degrees);
		g2.setColor(shape.getColor());
		g2.fill(shapeG2);
		velY = 1; //set initial velocity on y
		getNextShape();
	}

	private Area buildShape(ShapeCharWithColor shape, int degrees) {
		//This function uses the first square as the original parameter to set the relative position of the other squares
		getShape(shape, degrees); //full set of square (x, y) in a 4x4 matrix -> {x1, y1, x2, y2, x3, y3, x4, y4}		

		Area finalArea = new Area(); //used to combine several shapes together
		int paraX = location[0], paraY = location[1]; //x1 and y1, used as the original parameter
		int diffX, diffY; //relative position
		xLeft = xRight = 0; //track the breadth distribution on x-axis
		yDown = 0; //track the depth distribution on y-axis
		int yMax = 0; //the max index of y for this shape as a 4x4 matrix
		for (int i=0; i<squareNumber; i++) {
			diffX = (location[i*2] - paraX) * squareSize;
			diffY = (location[i*2+1] - paraY) * squareSize;
			Area temp = new Area(new Rectangle2D.Double(x+diffX, y+diffY, squareSize, squareSize));
			finalArea.add(temp);
			xLeft = Math.min(diffX, xLeft);
			xRight = Math.max(diffX, xRight);
			yDown = Math.max(diffY, yDown);
			yMax = Math.max(yMax, location[i*2+1]);
		}

		yMax++; //index starts from 0, so we need to add 1 here
		heightsInShape.clear(); //clear previous contents as this is a global variable
		heightsInShapeWithBlank.clear();
		int xTopLeft = location[0];
		int yTop = location[1];
		for (int i=0; i<2*squareNumber; i+=2) {
			int temp = location[i]-xTopLeft;
			if (!heightsInShape.containsKey(temp)) { //the shapeLocation[] is traversed from top to bottom, so the higher square is always reached first
				heightsInShape.put(temp, yMax-location[i+1]);
			}
			heightsInShapeWithBlank.put(temp, Math.max(location[i+1]-yTop, heightsInShapeWithBlank.getOrDefault(temp, 0)));
		}
		return finalArea;
	}

	private void getShape(ShapeCharWithColor shape, int degrees) {
		currShapeArray = PieceShapeInChar.rotateShapes(shape.getShape(), degrees);
		getShapeHelper(currShapeArray);
	}

	private void getShapeHelper(char[] currShapeArray) {
		for (int i=0, j=0; i<charRow*charCol; i++) {
			if (currShapeArray[i] == 'X') {
				location[j] = i % 4;
				location[j+1] = i / 4;
				j += 2;
			}
		}
	}

	private void getNextShape() {
		if (y == boardHeight - squareSize - yDown) { //reach the bottom of the board
			isNewRound = true;
			return ;
		}
		else {
			isNewRound = false;
		}
		for (int i : heightsInShapeWithBlank.keySet()) { //reach the square beneath
			if (squarePiled[x/squareSize+i][y/squareSize+heightsInShapeWithBlank.get(i)+1]) {
				isNewRound = true;
			}
		}
	}

	private void piledUP() {
		if (bump == -1) { //bump with the bottom line
			int xAbs = x / squareSize; //xAbs is the absolute x_index
			for (int i=xLeft/squareSize; i<xRight/squareSize+1; i++) {
				int curr = xAbs + i;
				Arrays.fill(squarePiled[curr], yPainted[curr]-heightsInShape.get(i), yPainted[curr], true);
				yPainted[curr] -= heightsInShape.get(i);
			}
		}
		else { //bump with other squares
			//find the bottom square of bumping x
			int xRel = bump - x/squareSize; //xRel is the relative x_index
			int xBottom = location[0] + xRel;
			int yBottom = 0;
			for (int i=0; i<location.length; i+=2) {
				if (location[i] == xBottom) {
					yBottom = Math.max(yBottom, location[i+1]);
				}
			}

			//change location array in relative to (xBottom, yBottom)
			for (int i=0; i<location.length; i+=2) {
				location[i] -= xBottom;
				location[i+1] -= yBottom;
			}

			//mark in squarePiled[] and yPainted[] with the absolute x_index and y_index
			int xAbs = bump;
			int yAbs = yPainted[bump] - 1;
			for (int i=0; i<location.length; i+=2) {
				squarePiled[xAbs+location[i]][yAbs+location[i+1]] = true;
				yPainted[xAbs+location[i]] = Math.min(yAbs+location[i+1], yPainted[xAbs+location[i]]);
			}
		}

	}

	private void checkIfStop() {
		if (y <= squareSize*squareNumber) {
			isFinished = true;
			status.setText("Good Job! You've earned " + count);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (y >= boardHeight - squareSize - yDown) { //instantly stop if reach the bottom
			velX = 0;
			velY = 0;
			bump = -1;
		}
		else {
			for (int i : heightsInShapeWithBlank.keySet()) {
				if (squarePiled[x/squareSize+i][y/squareSize+heightsInShapeWithBlank.get(i)+1]) { //instantly stop if reach the square piled up
					velX = 0;
					velY = 0;
					bump = x/squareSize + i;
					break;
				}
			}
		}

		if (x <= -xLeft) { //stop moving x if reach the left or right bound
			if (velX < 0) {
				velX = 0;
			}
		}
		else if (x >= boardWidth - 2*squareSize - xRight) {
			if (velX > 0) {
				velX = 0;
			}
		}

		x += velX;
		y += velY;
		repaint(); //repaint() contains update() and paint(), where update() goes first and clears the whole graph
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); //each key has its own unique code
		if (code == KeyEvent.VK_LEFT) {
			velX = -1;
		}
		else if (code == KeyEvent.VK_RIGHT) {
			velX = 1;
		}
		else if (code == KeyEvent.VK_DOWN) {
			velX = 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		velX = 0;
	}

	public void keyTyped(KeyEvent e) {}

}
