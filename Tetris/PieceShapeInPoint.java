/**
 * @author AJWuu
 */

package tetris;

import java.awt.Point;
import java.util.List;
import java.util.Random;

public class PieceShapeInPoint {

	public static final Point[][] allShapesInPoint = {
			{new Point(0,1), new Point(1,1), new Point(1,0), new Point(2,0)}, //Z-shape
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2)},
			{new Point(0,1), new Point(1,1), new Point(1,0), new Point(2,0)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2)},
			{new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1)}, //S-shape
			{new Point(0,2), new Point(0,1), new Point(1,1), new Point(2,0)},
			{new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1)},
			{new Point(0,2), new Point(0,1), new Point(1,1), new Point(2,0)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0)}, //I-shape (Line)
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0)},
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(1,1)}, //T-shape
			{new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2)},
			{new Point(0,1), new Point(1,1), new Point(2,1), new Point(1,0)},
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,1)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,0)}, //Square-shape
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,0)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,0)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,0)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1)}, //L-shape
			{new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1)},
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0)},
			{new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2)}, //J-shape
			{new Point(0,1), new Point(0,0), new Point(1,0), new Point(2,0)},
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,2)},
			{new Point(0,1), new Point(1,1), new Point(2,1), new Point(2,0)}
	};
	
	public static Point[] rotateShapes(ShapePointWithColor shape, int degrees) {
		degrees /= 90;
		switch (degrees) {
		case 0:
			return shape.getShape();
		case 1:
			return allShapesInPoint[shape.getIndex() + 1];
		case 2:
			return allShapesInPoint[shape.getIndex() + 2];
		case 3:
			return allShapesInPoint[shape.getIndex() + 3];
		}
		return null;
	}
	
	public ShapePointWithColor getRandomShape() {
		List<ShapePointWithColor> list = ShapePointWithColor.buildList();
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}
	
}
