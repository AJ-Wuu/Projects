/**
 * @author AJWuu
 */

package parenthesization;

public class Main {
	
	//There are 4 ways in total: ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T))

	public static void main(String[] args) {
		char symbols[] = "TTFT".toCharArray();
		char operators[] = "|&^".toCharArray();
		int n = symbols.length;
		System.out.println(MemoCount.countParenthesis(symbols, operators, n));

        String Symbols = "TTFT";
        String Operators = "|&^";
        StringBuilder S = new StringBuilder();
        int j = 0;
        for (int i=0; i<Symbols.length(); i++){
            S.append(Symbols.charAt(i));
            if (j < Operators.length())
                S.append(Operators.charAt(j++));
        }
        int N = S.length();
        System.out.println(TopDownCount.countWays(N, S.toString()));
	}
	
}
