/**
 * @author AJWuu
 */

package textJustification;

import java.util.PriorityQueue;
import java.util.Queue;

public class GreedyWithoutSpaceArrangement {

	//Microsoft Word uses Greedy.
	//This code implements the greedy from the first word, and pads the right side with spaces until reaching the max width of the line.
	
	private static int[] findRight(int left, String[] strings, int limit, int totalWasteSquare) {
		int right = left;
		int sum = strings[right++].length();

		while (right < strings.length && (sum + 1 + strings[right].length()) <= limit) {
			sum += 1 + strings[right++].length();
		}
        totalWasteSquare += Math.pow((limit - sum), 2);
		return (new int[]{totalWasteSquare, (right - 1)});
	}
	
	private static void print(String[] strings, Queue<Integer> recordIndex, int totalWasteSquare, int n) {
		System.out.println("Greedy (Align Left):");
		while (recordIndex.size() > 1) {
			int i = recordIndex.poll();
			int j = recordIndex.peek();
			for (; i<j-1; i++) {
				System.out.print(strings[i] + " ");
			}
			System.out.print(strings[j-1]);
			System.out.println();
		}
		System.out.println("---\n" + "Greedy: wastes' squares sum to " + totalWasteSquare + "\n");
	}
	
	public static void textJustification(String[] strings, int limit) {
		int n = strings.length;
		Queue<Integer> recordIndex = new PriorityQueue<Integer>();
		int totalWasteSquare = 0;
		
        int left = 0, right = 0;
        recordIndex.add(left);
        while (left < strings.length) {
            int[] temp = findRight(left, strings, limit, totalWasteSquare);
            totalWasteSquare = temp[0];
            right = temp[1];
            left = right + 1;
            recordIndex.add(left);
        }
        
        print(strings, recordIndex, totalWasteSquare, n);
    }
    
}
