/**
 * @author AJWuu
 */

package editDistance;

public class Min {

	//Find minimum number operations to convert str1 to str2
	public static int min(int x, int y, int z) {
		int temp = Math.min(x, y);
		return Math.min(z, temp);
	}

}
