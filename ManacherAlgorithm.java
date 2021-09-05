package manacher;

public class ManacherAlgorithm {

    //Transform s into t (eg. if s = "aba", then t = "$#a#b#a#@"), where
    //'#' is interleaved to avoid even/odd-length palindromes uniformly.
    //The meaning of adding those bogus chars is to get an easy conversion that
    //we can treat the odd-length and even-length substrings in the same way.
    private static void transform(String s, char[] t) {
        t[0] = '$';
        t[s.length()*2 + 2] = '@';
        for (int i=0; i<s.length(); i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[s.length() * 2 + 1] = '#';
    }
	
    public static void Manacher(String s, char[] t, int[] p) {
        transform(s, t);

        int center = 0, right = 0;
        for (int i=1; i<t.length-1; i++) { //starts at the second char (the beginning of s)
            int mirror = 2 * center - i; //t[i] and t[mirror] are symmetric from center

            if (right > i) { //still within the previous palindrome string
                p[i] = Math.min(right - i, p[mirror]); //(right - i) is the maximum potential
            }

            while (t[i + (1 + p[i])] == t[i - (1 + p[i])]) { //expand palindrome centered at i
                p[i]++;
            }
            
            if (i + p[i] > right) { //palindrome centered at i expands past right
                center = i;
                right = i + p[i];
            }
        }
    }

    public static String longestPalindromicSubstring(String s, int[] p) {
        int length = 0; //length of longest palindromic substring
        int center = 0; //center of longest palindromic substring
        for (int i=1; i<p.length-1; i++) {
            if (p[i] > length) {
                length = p[i];
                center = i;
            }
        }
        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
    }

    public static String getEveryPalindromicSubstring(String s, int[] p, int i) {
    	//longest palindromic substring centered at index i/2
        int length = p[i + 2];
        int center = i + 2;
        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
    }

    public static void main(String[] args) {
        String s = "abaabc"; //"abacdfgdcabaabacdfdcaba";
        char[] t = new char[s.length() * 2 + 3]; //transformed string
        int[] p = new int[t.length]; //p[i] = length of longest palindromic substring of t, centered at i
        
        Manacher(s, t, p);
        
        System.out.println("The Longest Palindromic Substring is:");
        System.out.println(longestPalindromicSubstring(s, p));
        System.out.println("\nThe Finding Process is:");
        for (int i=0; i<2*s.length(); i++) {
        	System.out.println(i +  ": " + getEveryPalindromicSubstring(s, p, i));
        }
    }
	
}
