/**
 * @author AJWuu
 */

package tetris;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

public enum ShapeCharWithColor {
	Z (PieceShapeInChar.allShapesInChar[0], Color.black),
	S (PieceShapeInChar.allShapesInChar[1], Color.blue),
	I (PieceShapeInChar.allShapesInChar[2], Color.gray),
	T (PieceShapeInChar.allShapesInChar[3], Color.green),
	Square (PieceShapeInChar.allShapesInChar[4], Color.magenta),
	L (PieceShapeInChar.allShapesInChar[5], Color.yellow),
	J (PieceShapeInChar.allShapesInChar[6], Color.red);
	
	private final char[] pieceShape;
	private final Color pieceColor;
	
	ShapeCharWithColor(char[] shape, Color color) {
		this.pieceShape = shape;
		this.pieceColor = color;
	}
	
	public char[] getShape() {
		return this.pieceShape;
	}
	
	public Color getColor() {
		return this.pieceColor;
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
