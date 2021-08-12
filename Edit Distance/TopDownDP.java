/**
 * @author AJWuu
 */

package editDistance;

public class TopDownDP {

	public static int minDis(String s1, String s2, int m, int n, int[][] dp) {
		//If any string is empty, return the remaining characters of other string
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}

		if (dp[m][n] != -1) { //check if the recursive tree for given m & n has already been executed
			return dp[m][n];
		}

		if(s1.charAt(m-1) == s2.charAt(n-1)) {//If characters are equal, execute recursive function
			if (dp[m-1][n-1] == -1) {			
				return dp[m][n] = minDis(s1, s2, m-1, n-1, dp);		
			}	
			else {
				return dp[m][n] = dp[m-1][n-1];
			}
		}
		else { //If characters are not equal, find the minimum cost out of all 3 operations	
			int m1, m2, m3;
			
			if (dp[m-1][n] != -1) {
				m1 = dp[m-1][n];	
			}		
			else {
				m1 = minDis(s1, s2, m-1, n, dp);	
			}		

			if(dp[m][n-1] != -1) {			
				m2 = dp[m][n-1];		
			}		
			else {
				m2 = minDis(s1, s2, m, n-1, dp);	
			}								

			if(dp[m-1][n-1] != -1) {
				m3 = dp[m-1][n-1];	
			}
			else {
				m3 = minDis(s1, s2, m-1, n-1, dp);	
			}
			
			return dp[m][n] = 1 + Min.min(m1, m2, m3);	
		}
	}

}
