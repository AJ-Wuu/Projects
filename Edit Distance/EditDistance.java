/**
 * @author AJWuu
 */

package editDistance;

import java.util.Arrays;

public class EditDistance {

	public static void main(String args[]) {
		String str1 = "sunday";
		String str2 = "saturday";
		System.out.println(Naive.editDist(str1, str2, str1.length(), str2.length()));
		System.out.println(BasicDP.editDistDP(str1, str2, str1.length(), str2.length()));

		String str3 = "food";
		String str4 = "money";
		SpaceEfficientDP.EditDistDP(str3, str4);

		String str5 = "voldemort";
		String str6 = "dumbledore";
		int m = str5.length(), n = str6.length();
		int[][] dp = new int[m+1][n+1];
		for(int i=0; i<m+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.print(TopDownDP.minDis(str5, str6, m, n, dp));
	}

}
