/**
 * @author AJWuu
 */

package editDistance;

public class SpaceEfficientDP {

	//Time Complexity: O(m x n)
	//Space Complexity: O(m)
	
	// A Space efficient Dynamic Programming
	// based Java program to find minimum
	// number operations to convert str1 to str2

	public static void EditDistDP(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();

		// Create a DP array to memoize result
		// of previous computations
		int [][]DP = new int[2][len1 + 1];


		// Base condition when second String
		// is empty then we remove all characters
		for (int i = 0; i <= len1; i++)
			DP[0][i] = i;

		// Start filling the DP
		// This loop run for every
		// character in second String
		for (int i = 1; i <= len2; i++)
		{
		
			// This loop compares the char from
			// second String with first String
			// characters
			for (int j = 0; j <= len1; j++)
			{
			
				// if first String is empty then
				// we have to perform add character
				// operation to get second String
				if (j == 0)
					DP[i % 2][j] = i;

				// if character from both String
				// is same then we do not perform any
				// operation . here i % 2 is for bound
				// the row number.
				else if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
					DP[i % 2][j] = DP[(i - 1) % 2][j - 1];
				}

				// if character from both String is
				// not same then we take the minimum
				// from three specified operation
				else {
					DP[i % 2][j] = 1 + Math.min(DP[(i - 1) % 2][j],
										Math.min(DP[i % 2][j - 1],
											DP[(i - 1) % 2][j - 1]));
				}
			}
		}

		// after complete fill the DP array
		// if the len2 is even then we end
		// up in the 0th row else we end up
		// in the 1th row so we take len2 % 2
		// to get row
		System.out.print(DP[len2 % 2][len1] +"\n");
	}
	
}
