/**
 * @author AJWuu
 */

package textJustification;

public class TextJustification {

	/*
	 * Generally, DP tends to be better than Greedy.
	 * Note: The following example does not show any generality -- it's just an example.
	 * For example, 10 letters per line for "Jason Roy likes to code"
	 * Greedy: Jason Roy -> 9 used, 1 wasted
	 *         likes to  -> 8 used, 2 wasted
	 *         code      -> 4 used, 6 wasted
	 * DP: Jason     -> 5 used, 5 wasted
	 *     Roy likes -> 9 used, 1 wasted
	 *     to code   -> 7 used, 3 wasted
	 * In linear, both waste 9 letters' space;
	 * In square, Greedy wastes 41 (1+4+36) while DP wastes 35 (25+1+9).
	 * So, DP tends to be better in this case.
	 */

	public static void main(String[] args) {
		String[] string = {"Jason", "Roy", "likes", "to", "code"};
		//If this maxLength is shorter than the longest word (eg. we put 10 here but "programming" has 11 letters), it will get an out-of-bound.
		DP.textJustification(string, 10);
		GreedyWithoutSpaceArrangement.textJustification(string, 10);
		GreedyWithSpaceArrangement.textJustification(string, 10);
	}

}
