/**
 * @author AJWuu
 */

package textJustification;

import java.util.ArrayList;

public class GreedyWithSpaceArrangement { //This method applys the "Justify" function in Microsoft Word.

	//Microsoft Word uses Greedy.
	//This code implements the greedy from the first word, and pads the right side with spaces until reaching the max width of the line.
	
	private static int findRight(int left, String[] strings, int limit) {
        	int right = left;
        	int sum = strings[right++].length();
        
        	while (right < strings.length && (sum + 1 + strings[right].length()) <= limit) {
            		sum += 1 + strings[right++].length();
        	}
            
        	return (right-1);
    	}
	
    	private static String blank(int length) {
        	return new String(new char[length]).replace('\0', ' ');
    	}
    
    	private static String padResult(String result, int limit) {
        	return result + blank(limit - result.length());
    	}
    
	private static int stringsLength(int left, int right, String[] strings) {
        	int stringsLength = 0;
        	for (int i=left; i<=right; i++) {
        		stringsLength += strings[i].length();
        	}
        	return stringsLength;
    	}

	private static String justify(int left, int right, String[] strings, int limit) {
        	if (right - left == 0) {
        		return padResult(strings[left], limit);
        	}
        
        	boolean isLastLine = (right == strings.length - 1);
        	int numSpaces = right - left;
        	int totalSpace = limit - stringsLength(left, right, strings);
        
        	String space = isLastLine ? " " : blank(totalSpace / numSpaces);
        	int remainder = isLastLine ? 0 : totalSpace % numSpaces;
        
        	StringBuilder result = new StringBuilder();
        	for (int i=left; i<=right; i++) {
            		result.append(strings[i])
                  	      .append(space)
                  	      .append(remainder-- > 0 ? " " : "");
        	}
        
        	return padResult(result.toString().trim(), limit);
		//trim() eliminates leading and trailing spaces.
		//The unicode value of space character is '\u0020'.
		//trim() checks the unicode value before and after the string, if it exists then removes the spaces and returns the omitted string.
    	}
	
	public static void textJustification(String[] strings, int limit) {
        	int left = 0;
        	ArrayList<String> result = new ArrayList<String>();
        
        	while (left < strings.length) {
            		int right = findRight(left, strings, limit);
            		result.add(justify(left, right, strings, limit));
            		left = right + 1;
        	}
        
		System.out.println("Greedy (Justify):");
        	for (int i=0; i<result.size(); i++) {
        		System.out.println(result.get(i));
        	}
    	}
    
}
