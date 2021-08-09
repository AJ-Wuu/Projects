/**
 * @author AJWuu
 */

package knapsack;

public class DP {

		// Returns the maximum value that can be put in a knapsack
		// of capacity W
	public static int knapSack(int W, int wt[], int val[], int n)
		{
			int i, w;
			int K[][] = new int[n + 1][W + 1];

			// Build table K[][] in bottom up manner
			for (i = 0; i<= n; i++) {
				for (w = 0; w<= W; w++) {
					if (i == 0 || w == 0)
						K[i][w] = 0;
					else if (wt[i - 1]<= w)
						K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
					else
						K[i][w] = K[i - 1][w];
				}
			}

			return K[n][W];
		}
	
}
