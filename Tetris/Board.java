/**
 * @author AJWuu
 */

package tetris;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener, KeyListener {

	private static int squareSize, boardWidth, boardHeight, squareNumber, charRow, charCol;
	private static Frame frame;
	private static JPanel board;
	private static boolean roundFinished = true;

	Timer timer = new Timer(5, this);
	int x, y, velX = 0, velY = 1, x_right = 0, y_right = 0;
	int[] xPoints, yPoints;
	ShapeCharWithColor shape;
	int degrees;
	char[] currShapeArray;
	Area shapeG2 = new Area();

	public Board(Frame jframe) {
		frame = jframe;
		boardWidth = frame.Width;
		boardHeight = frame.Height - 3*frame.labelSize - frame.buttonSize;
		squareSize = frame.squareSize;
		squareNumber = PieceShapeInChar.pieceNumber;
		charRow = PieceShapeInChar.pieceWidth;
		charCol = PieceShapeInChar.pieceHeight;

		x = boardWidth/2 - squareSize;
		y = 2;
		xPoints = new int[4 * squareNumber];
		yPoints = new int[4 * squareNumber];

		board = this;
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
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(0, squareSize*squareNumber, boardWidth, squareSize*squareNumber);
//		while (roundFinished) {
			shape = ShapeCharWithColor.J; //PieceShapeInChar.getRandomShape();
			shapeG2 = buildShape(shape);
			g2.setColor(shape.getColor());
//			stop(g2);		
//		}	
		g2.fill(shapeG2);
	}

	private Area buildShape(ShapeCharWithColor shape) {
		int[] shapeLocation = getShape(shape);
		Area fin = new Area(); //used to combine several shapes together
		int paraX = shapeLocation[0], paraY = shapeLocation[1];
		int diffX, diffY;
		for (int i=0; i<squareNumber; i++) {
			diffX = (shapeLocation[i*2] - paraX) * squareSize;
			diffY = (shapeLocation[i*2+1] - paraY) * squareSize;
			Area temp = new Area(new Rectangle2D.Double(x+diffX, y+diffY, squareSize, squareSize));
			fin.add(temp);
			x_right = Math.max(x+diffX, x_right);
			y_right = Math.max(y+diffY, y_right);
		}
		return fin;
	}

	private int[] getShape(ShapeCharWithColor shape) {
		degrees = 0;//PieceShapeInChar.getRandomDegrees();
		currShapeArray = PieceShapeInChar.rotateShapes(shape.getShape(), degrees);
		return getShapeHelper(currShapeArray);
	}

	private int[] getShapeHelper(char[] currShapeArray) {
		int[] location = new int[2*squareNumber];
		for (int i=0, j=0; i<charRow*charCol; i++) {
			if (currShapeArray[i] == 'X') {
				location[j] = i % 4;
				location[j+1] = i / 4;
				j += 2;
			}
		}
		return location;
	}

	private void stop(Graphics2D g2) {
		roundFinished = false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		x += velX;
		y += velY;
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
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

}
