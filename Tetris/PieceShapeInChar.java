/**
 * @author AJWuu
 */

package tetris;

import java.util.List;
import java.util.Random;

public class PieceShapeInChar {
	
	private static int pieceWidth = 4;
	private static int pieceHeight = 4;

	public static final char[][] allShapesInChar = {
			"....XX...XX.....".toCharArray(), //Z-shape
			".....XX.XX......".toCharArray(), //S-shape
			"X...X...X...X...".toCharArray(), //I-shape (Line)
			"XXX..X..........".toCharArray(), //T-shape
			".....XX..XX.....".toCharArray(), //Square-shape
			"X...X...XX......".toCharArray(), //L-shape
			".X...X..XX......".toCharArray()  //J-shape
	};
	
	private static char[] rotateHelper90(char[] oldShape) {
		char[] newShape = new char[oldShape.length];
		int x, y;
		for (int i=0; i<pieceWidth*pieceHeight; i++) {
			x = i % 4;
			y = i / 4;
			newShape[12 + y - x * pieceWidth] = oldShape[i];
		}
		return newShape; //Converting char Char to String can be done by String str = new String(charArray);
	}
	
	private static char[] rotateHelper180(char[] oldShape) {
		char[] newShape = new char[oldShape.length];
		int x, y;
		for (int i=0; i<pieceWidth*pieceHeight; i++) {
			x = i % 4;
			y = i / 4;
			newShape[15 - y * pieceWidth - x] = oldShape[i];
		}
		return newShape;
	}
	
	private static char[] rotateHelper270(char[] oldShape) {
		char[] newShape = new char[oldShape.length];
		int x, y;
		for (int i=0; i<pieceWidth*pieceHeight; i++) {
			x = i % 4;
			y = i / 4;
			newShape[3 + y + x * pieceWidth] = oldShape[i];
		}
		return newShape;
	}
	
	public static char[] rotateShapes(char[] shape, int degrees) {
		degrees /= 90;
		switch (degrees) {
		case 0: //0 degree
			return shape;
		case 1: //90 degrees
			return rotateHelper90(shape);
		case 2: //180 degrees
			return rotateHelper180(shape);
		case 3: //270 degrees
			return rotateHelper270(shape);
		}
		return null;
	}

	public static ShapeCharWithColor getRandomShape() {
		List<ShapeCharWithColor> list = ShapeCharWithColor.buildList();
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}
	
}
