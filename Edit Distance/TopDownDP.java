/**
 * @author AJWuu
 */

package editDistance;

public class TopDownDP {

	public static int minDis(String s1, String s2, int n, int m, int[][]dp) {
		//If any string is empty, return the remaining characters of other string
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}

		if (dp[n][m] != -1) { //check if the recursive tree for given n & m has already been executed
			return dp[n][m];
		}

		if(s1.charAt(n-1) == s2.charAt(m-1)) {//If characters are equal, execute recursive function
			if (dp[n-1][m-1] == -1) {			
				return dp[n][m] = minDis(s1, s2, n-1, m-1, dp);		
			}	
			else {
				return dp[n][m] = dp[n-1][m-1];
			}
		}
		else { //If characters are not equal, find the minimum cost out of all 3 operations	
			int m1, m2, m3;
			if (dp[n-1][m] != -1) {
				m1 = dp[n-1][m];	
			}		
			else {
				m1 = minDis(s1, s2, n-1, m, dp);	
			}		

			if(dp[n][m-1] != -1) {			
				m2 = dp[n][m-1];		
			}		
			else {
				m2 = minDis(s1, s2, n, m-1, dp);	
			}								

			if(dp[n-1][m-1] != -1) {
				m3 = dp[n-1][m-1];	
			}
			else {
				m3 = minDis(s1, s2, n-1, m-1, dp);	
			}	
			return dp[n][m] = 1 + Min.min(m1, m2, m3);	
		}
	}

}
