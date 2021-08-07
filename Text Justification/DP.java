/**
 * @author AJWuu
 */

package textJustification;

public class DP {
	
	//LaTeX use DP to wrap up words

	private static int[][] getWastedSquare(int[] lengths, int limit, int n) {
		int[][] wastedSquare = new int[n][n];
		int subTotal = 0;
		for (int i=0; i<n; i++) {
			for (int j=i; j<n; j++) {
				subTotal = j - i; //add the length taken by space between words (" ")
				for (int k=i; k<=j; k++) {
					subTotal += lengths[k];
				}
				if (subTotal <= limit) {
					wastedSquare[i][j] = (int)Math.pow(limit-subTotal, 2);
				}
				else {
					wastedSquare[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		return wastedSquare;
	}

	private static void initialize(int[] countSquare, int[] recordIndex, int n, int[][] wastedSquare) {
		for (int i=0; i<n-1; i++) {
			recordIndex[i] = -1;
		}
		countSquare[n-1] = wastedSquare[n-1][n-1];
		recordIndex[n-1] = n;
	}

	private static void split(int i, int j, int[] countSquare, int[] recordIndex, int[][] wastedSquare) {
		int temp = 0;
		for (; j>i; j--) {
			temp = wastedSquare[i][j-1]; //check if could split from j, so j should be the beginning of the new line, and j-1 is the end of current line
			if (temp < Integer.MAX_VALUE) {
				temp += countSquare[j];
				if (recordIndex[i] == -1 || temp < countSquare[i]) {
					countSquare[i] = temp;
					recordIndex[i] = j;
				}
			}
		}
	}

	private static void wrapDPHelper(int i, int[] countSquare, int[] recordIndex, int[][] wastedSquare, int n) {
		for (int j=n-1; j>i; j--) { //both i and j are pointers, i is always less than or equal to j, and j always re-starts from n-1
			int temp1 = wastedSquare[i][j];
			if (temp1 < Integer.MAX_VALUE) { //can put string[i] to string[j] in one line
				if (recordIndex[i] == -1 || temp1 < countSquare[i]) { //never touched j OR get a smaller result
					countSquare[i] = temp1;
					recordIndex[i] = j + 1;
				}
			}
			else { //cannot put string[i] to string[j] in one line, so check to see where to split
				split(i, j, countSquare, recordIndex, wastedSquare);
				break;
			}
		}
	}

	private static void print(String[] strings, int[] recordIndex, int totalSquare, int n) {
		int last = 0;
		for (int i=0; i<n; ) {
			last = recordIndex[i];
			for (int j=i; j<last; j++) {
				System.out.print(strings[j] + " ");
			}
			System.out.println();
			i = last;
		}
		System.out.println("---\n" + "DP: wastes' squares sum to " + totalSquare + "\n");
	}

	private static void wrapDP1(String[] strings, int[][] wastedSquare, int n) {
		//Initialize
		int[] countSquare = new int[n]; //store the minimum wastedSquare taken till the current string
		int[] recordIndex = new int[n]; //store the index of the next-but-not-included-in-the-current-line string
		initialize(countSquare, recordIndex, n, wastedSquare);

		//Update
		for (int m=n-2; m>=0; m--) { //go over backwards
			wrapDPHelper(m, countSquare, recordIndex, wastedSquare, n);
		}

		//Output
		print(strings, recordIndex, countSquare[0], n);
	}

	//This is more concrete than wrapDP1
	private static void wrapDP2(String[] strings, int[][] wastedSquare, int n) {
		int[] countSquare = new int[n];
		int[] recordIndex = new int[n];
		for (int i=n-1; i>=0; i--) {
			countSquare[i] = wastedSquare[i][n-1]; //all initialize as the cost from i to n-1
			recordIndex[i] = n;
			for (int j=n-1; j>i; j--) {
				if (wastedSquare[i][j-1] == Integer.MAX_VALUE) {
					continue;
				}
				if (countSquare[i] > countSquare[j] + wastedSquare[i][j-1]) {
					countSquare[i] = countSquare[j] + wastedSquare[i][j-1];
					recordIndex[i] = j;
				}
			}
		}
		print(strings, recordIndex, countSquare[0], n);
	}

	public static void textJustification(String[] strings, int limit) {
		int n = strings.length;
		int[] lengths = new int[n];
		for (int i=0; i<n; i++) {
			lengths[i] = strings[i].length();
		}

		//store the squares of wasted space, eg. wasted 5, store 25
		int[][] wastedSquare = getWastedSquare(lengths, limit, n);

		wrapDP1(strings, wastedSquare, n);
		wrapDP2(strings, wastedSquare, n);
	}

}
