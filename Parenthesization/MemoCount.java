/**
 * @author AJWuu
 */

package parenthesization;

public class MemoCount {

	//Time Complexity: O(n^3)
	//Space Complexity: O(n^2)
	
	public static int countParenthesis(char symb[], char oper[], int n) {
		int T[][] = new int[n][n]; //T(i, j) represents the number of ways to parenthesize the symbols between i and j (both inclusive)
		int F[][] = new int[n][n];

		//Fill diagonal entries
		for (int i=0; i<n; i++) {
			T[i][i] = (symb[i] == 'T') ? 1 : 0;
			F[i][i] = (symb[i] == 'F') ? 1 : 0;
		}

		//Fill Matrix[i][i+1], Matrix[i][i+2], Matrix[i][i+3], ... in order
		for (int gap=1; gap<n; gap++) {
			for (int i=0, j=gap; j<n; i++, j++) {
				T[i][j] = F[i][j] = 0;
				for (int g=0; g<gap; g++) {
					//Find place of parenthesization
					int k = i + g;

					//Store Total[i][k] and Total[k+1][j]
					int ikTotal = T[i][k] + F[i][k];
					int kjTotal = T[k + 1][j] + F[k + 1][j];

					//Follow the recursive formulas according to the current operator
					if (oper[k] == '&') {
						T[i][j] += T[i][k] * T[k + 1][j];
						F[i][j]	+= (ikTotal * kjTotal - T[i][k] * T[k + 1][j]);
					}
					if (oper[k] == '|')	{
						F[i][j] += F[i][k] * F[k + 1][j];
						T[i][j] += (ikTotal * kjTotal - F[i][k] * F[k + 1][j]);
					}
					if (oper[k] == '^')	{
						T[i][j] += F[i][k] * T[k + 1][j] + T[i][k] * F[k + 1][j];
						F[i][j] += T[i][k] * T[k + 1][j] + F[i][k] * F[k + 1][j];
					}
				}
			}
		}
		return T[0][n - 1];
	}

}
