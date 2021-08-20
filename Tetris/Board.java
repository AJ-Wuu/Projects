/**
 * @author AJWuu
 */

package tetris;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board {

	private static final int squareSize = 20;
	private static int boardWidth, boardHeight; //the number of squares for the board's width and height
	public static JPanel board;
	private static JLabel status;

	public Board(Frame frame) {
		board = new JPanel(null);
		//1. change the Layout in the parenthesis (to BorderLayout, for example) for better arranging the place of buttons
		//   Note that the locations would be strictly aligned to the layout defaults, and setBounds() would not work
		//2. change the layout to null will make the panel in the "Absolute Layout",
		//   where it's able to set the position of the JLabel and JButton with setBounds()
		status = frame.status;
		boardWidth = frame.Width;
		boardHeight = frame.Height;
		freshBoard(squareSize);
	}

	private void freshBoard(int squareSize) {
		board.setPreferredSize(new Dimension(boardWidth, boardHeight));
		board.setBackground(Color.white);
		board.add(new Buttons(board, status, boardWidth, boardHeight));
		board.add(new KeyMoves(board, squareSize, boardWidth/2));  
	}

	public static void start() {
		System.out.println("Start");
//		Tiles tiles = new Tiles(squareSize, boardWidth, boardHeight, (int)(boardHeight/squareSize), (int)(boardWidth/squareSize), board);
	}

	public static void pause() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException except) {
			except.printStackTrace();
		}
	}

	public static void stop() {
		Buttons.pauseButton.setEnabled(false);
		try {
			Thread.sleep(5);
		} catch (InterruptedException except) {
			except.printStackTrace();
		}
		status.setText("You've earned: " + String.valueOf(Tiles.numLinesRemoved));
	}


	//	private Point[] getNewPiece() {
	//		ShapePointWithColor shape = PieceShapeInPoint.getRandomShape();
	//		Point[] piece = PieceShapeInPoint.rotateShapes(shape, PieceShapeInPoint.getRandomDegrees());
	//		int[] XYlength = PieceShapeInPoint.XYlength(piece);
	//		posX = (numSquareWidth - XYlength[0]) / 2; //always start from the middle
	//		posY = numSquareHeight - 1 + XYlength[1];
	//
	//		if (!moveAction(piece, posX, posY, XYlength)) {
	//			piece = blankPiece;
	//			timer.stop();
	//			String message = String.format("Game over. Score: %d", numLinesRemoved);
	//			status.setText(message);
	//		}
	//		return piece;
	//	}
	//
	//	private boolean moveAction(Point[] newPiece, int newX, int newY, int[] XYlength) {
	//		for (int i=0; i<shapeSize; i++) {
	//			int x = newX + XYlength[0];
	//			int y = newY - XYlength[1];
	//			if (x < 0 || x >= numSquareWidth || y < 0 || y >= numSquareHeight) {
	//				return false;
	//			}
	//		}
	//		currPiece = newPiece;
	//		posX = newX;
	//		posY = newY;
	//		repaint(); //Calling JPanel.repaint() will have all the component within the JPanel to repaint()
	//		return true;
	//	}
	//
	//	private class GameCycle implements ActionListener {
	//		@Override
	//		public void actionPerformed(ActionEvent event) {
	//			runGameCycle();
	//		}
	//	}
	//
	//	private void runGameCycle() {
	//		update();
	//		repaint();
	//	}
	//
	//	private void update() {
	//		if (isPaused) {
	//			return ;
	//		}
	//		if (isFinished) {
	//			isFinished = false;
	//			currPiece = getNewPiece();
	//		}
	//		else {
	//			oneLineDown();
	//		}
	//	}
	//
	//	private void oneLineDown() {
	//		int[] XYlength = PieceShapeInPoint.XYlength(currPiece);
	//		if (!moveAction(currPiece, posX, posY - 1, XYlength)) {
	//			pieceDropped(XYlength);
	//		}
	//	}
	//
	//	private void pieceDropped(int[] XYlength) {
	//		removeFullLines();
	//		if (!isFinished) {
	//			currPiece = getNewPiece();
	//		}
	//	}
	//
	//	private void removeFullLines() {
	//		int numFullLines = 0;
	//		for (int i=numSquareHeight-1; i>=0; i--) { //from bottom to top
	//			boolean isFullLine = true;
	//			for (int j=0; j<numSquareWidth; j++) { //from left to right
	//				if (shapeAt(j, i) == blankPiece) {
	//					isFullLine = false;
	//					break;
	//				}
	//			}
	//
	//			if (isFullLine) {
	//				numFullLines++;
	//				for (int k = i; k < boardHeight - 1; k++) {
	//					for (int j = 0; j < boardWidth; j++) {
	//						board[(k * boardWidth) + j] = shapeAt(j, k + 1);
	//					}
	//				}
	//			}
	//		}
	//
	//		if (numFullLines > 0) {
	//			numLinesRemoved += numFullLines;
	//			status.setText(String.valueOf(numLinesRemoved));
	//			isFinished = true;
	//			currPiece.setShape(blankPiece);
	//		}
	//	}
	//
	//	private void Pause() {
	//		if (isPaused) {
	//			status.setText("Paused");
	//		}
	//		else {
	//			status.setText(String.valueOf(numLinesRemoved));
	//		}
	//		repaint();
	//	}

}
