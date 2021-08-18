/**
 * @author AJWuu
 */

package tetris;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public enum ShapePointWithColor {
	Z (PieceShapeInPoint.allShapesInPoint[0], Color.black, 0),
	S (PieceShapeInPoint.allShapesInPoint[4], Color.blue, 4),
	I (PieceShapeInPoint.allShapesInPoint[8], Color.gray, 8),
	T (PieceShapeInPoint.allShapesInPoint[12], Color.green, 12),
	Square (PieceShapeInPoint.allShapesInPoint[16], Color.magenta, 16),
	L (PieceShapeInPoint.allShapesInPoint[20], Color.yellow, 20),
	J (PieceShapeInPoint.allShapesInPoint[24], Color.red, 24);
	
	private final Point[] pieceShape;
	private final Color pieceColor;
	private final int pieceIndex;
	
	ShapePointWithColor(Point[] shape, Color color, int index) {
		this.pieceShape = shape;
		this.pieceColor = color;
		this.pieceIndex = index;
	}

	public Point[] getShape() {
		return this.pieceShape;
	}
	
	public Color getColor() {
		return this.pieceColor;
	}
	
	public int getIndex() {
		return this.pieceIndex;
	}
	
	public static List<ShapePointWithColor> buildList() {
		List<ShapePointWithColor> list = new ArrayList<ShapePointWithColor>();
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
