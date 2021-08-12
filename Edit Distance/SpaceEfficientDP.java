/**
 * @author AJWuu
 */

package editDistance;

public class SpaceEfficientDP {

	//Time Complexity: O(m x n)
	//Space Complexity: O(m)
	
	//This method is Space efficient

	public static void EditDistDP(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();

		//Create a DP array to memoize result of previous computations
		int[][] DP = new int[2][len1+1];


		//Base condition: when second string is empty then we remove all characters
		for (int i=0; i<=len1; i++) {
			DP[0][i] = i;
		}
		
		for (int i=1; i<=len2; i++) { //for every character in second String
			for (int j=0; j<=len1; j++) { //compare the char from second String with first String characters
				if (j == 0) { //first String is empty, perform add character operation to get second String
					DP[i % 2][j] = i;
				}
				else if (str1.charAt(j - 1) == str2.charAt(i - 1)) { //characters from both Strings are same, no operation
					DP[i % 2][j] = DP[(i - 1) % 2][j - 1]; //(i % 2) is for bounding the row number.
				}
				else { //characters from both strings are not same, take the minimum from three specified operation
					DP[i % 2][j] = 1 + Min.min(DP[(i - 1) % 2][j],
											   DP[i % 2][j - 1],
											   DP[(i - 1) % 2][j - 1]);
				}
			}
		}

		//if the len2 is even, then end up in row[0];
		//else, end up in row[1]
		System.out.print(DP[len2 % 2][len1] +"\n");
	}
	
}
