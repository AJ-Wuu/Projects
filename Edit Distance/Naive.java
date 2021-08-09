/**
 * @author AJWuu
 */

package editDistance;

public class Naive {

	public static int editDist(String str1, String str2, int m,	int n) {
		//first string is empty, insert all characters of second string into first
		if (m == 0) {
			return n;
		}

		//second string is empty, remove all characters of first string
		if (n == 0) {
			return m;
		}

		//last characters of two strings are same, ignore last characters and get count for remaining strings.
		if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
			return editDist(str1, str2, m - 1, n - 1);
		}

		//last characters are not same, consider all three operations on last character of first string, 
		//recursively compute minimum cost for all three operations and take minimum of three values
		return 1 + Min.min(editDist(str1, str2, m, n - 1), // Insert
				editDist(str1, str2, m - 1, n), // Remove
				editDist(str1, str2, m - 1,	n - 1) // Replace
				);
	}

}
