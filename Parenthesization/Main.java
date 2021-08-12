/**
 * @author AJWuu
 */

package parenthesization;

public class Main {
	
	//There are 4 ways in total: ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T))

	public static void main(String[] args) {
		String Symbols = "TTFT";
        	String Operators = "|&^";
        
		char symbols[] = Symbols.toCharArray();
		char operators[] = Operators.toCharArray();
		int n = symbols.length;
		System.out.println(MemoCount.countParenthesis(symbols, operators, n));

        	StringBuilder S = new StringBuilder();
        	int j = 0;
        	for (int i=0; i<Symbols.length(); i++){
            		S.append(Symbols.charAt(i));
            		if (j < Operators.length()) {
                		S.append(Operators.charAt(j++));
			}
        	}
        	int N = S.length();
        	System.out.println(TopDownCount.countWays(N, S.toString()));
	}
	
}
