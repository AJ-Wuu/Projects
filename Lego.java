package lego;

import java.io.*;
import java.util.*;

class Result {
	
    static int mod = 1000000007;
    
    static int[][] ways = new int[1001][1001];
    static int[][] waysRestricted = new int[1001][1001];
    
    public Result() {
        for (int[] w : ways) {
        	Arrays.fill(w,-1);
        }
        for (int[] w : waysRestricted) {
        	Arrays.fill(w,-1);
        }
    }
    
    public static int solve(int n,int m){
        if (ways[n][m] != -1) {
        	return ways[n][m];
        }
        long ans;
        if (m == 1) {
        	ans = 1;
        }
        else if (n == 1) {
            if (m <= 4) {
                ans = 2 * solve(1, m-1);
            }
            else {
                ans = solve(1, m-1);
                ans = (ans + solve(1, m-2)) % mod;
                ans = (ans + solve(1, m-3)) % mod;
                ans = (ans + solve(1, m-4)) % mod;
            }
        }
        else {
            ans = 1;
            int one = solve(1,m);
            for (int i=0; i<n; i++) {
                ans = (ans * one) % mod;
            }
        }
        ways[n][m] = (int)ans;
        return ways[n][m];
    }
    
    public static int solveRestrict(int n, int m) {
        if (waysRestricted[n][m] != -1) {
        	return waysRestricted[n][m];
        }
        long ans;
        if (m == 1) {
        	ans=1;
        }
        else {
            ans = solve(n, m);
            for (int i=1; i<m; i++) {
                long a = solve(n,i);
                a = (a * solveRestrict(n, m-i)) % mod;
                ans -= a;
                if (ans < 0) {
                    ans+=mod;
                }
            }
        }
        waysRestricted[n][m] = (int)ans;
        return waysRestricted[n][m];
    }
    
    public static int legoBlocks(int n, int m) {
        return solveRestrict(n,m);
    }

}

public class Lego {
	
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//        int t = Integer.parseInt(bufferedReader.readLine().trim());
//
//        for (int tItr=0; tItr<t; tItr++) {
//            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
//            int n = Integer.parseInt(firstMultipleInput[0]);
//            int m = Integer.parseInt(firstMultipleInput[1]);
//            int result = Result.legoBlocks(n, m);
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
//        }
//
//        bufferedReader.close();
//        bufferedWriter.close();
    	Result r = new Result();
    	System.out.println(Result.legoBlocks(2, 2));
    	System.out.println(Result.legoBlocks(3, 2));
    	System.out.println(Result.legoBlocks(2, 3));
    	System.out.println(Result.legoBlocks(4, 4));
    }
}
