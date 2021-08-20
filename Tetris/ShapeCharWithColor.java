/**
 * @author AJWuu
 */

package tetris;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

public enum ShapeCharWithColor {
	Z (PieceShapeInChar.allShapesInChar[0], Color.blue, 3, 2),
	S (PieceShapeInChar.allShapesInChar[1], Color.cyan, 3, 2),
	I (PieceShapeInChar.allShapesInChar[2], Color.gray, 1, 4),
	T (PieceShapeInChar.allShapesInChar[3], Color.green, 3, 2),
	Square (PieceShapeInChar.allShapesInChar[4], Color.magenta, 2, 2),
	L (PieceShapeInChar.allShapesInChar[5], Color.yellow, 2, 3),
	J (PieceShapeInChar.allShapesInChar[6], Color.red, 2, 3);
	
	private final char[] pieceShape;
	private final Color pieceColor;
	private final int Xlength;
	private final int Ylength;
	
	ShapeCharWithColor(char[] shape, Color color, int X, int Y) {
		this.pieceShape = shape;
		this.pieceColor = color;
		this.Xlength = X;
		this.Ylength = Y;
	}
	
	public char[] getShape() {
		return this.pieceShape;
	}
	
	public Color getColor() {
		return this.pieceColor;
	}

	public int getXLength() {
		return this.Xlength;
	}
	
	public int getYLength() {
		return this.Ylength;
	}
	
	public static List<ShapeCharWithColor> buildList() {
		List<ShapeCharWithColor> list = new ArrayList<ShapeCharWithColor>();
		list.add(Z);
		list.add(S);
		list.add(I);
		list.add(T);
		list.add(Square);
		list.add(L);
		list.add(J);
		return list;
	}
}
