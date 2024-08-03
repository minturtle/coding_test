package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem2839 {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;

            int result = execute(n);
            bw.write(Integer.toString(result == Integer.MAX_VALUE ? -1 : result));
            bw.flush();
        }
    }

    static int execute(int n){
        if(n < 0){
            return Integer.MAX_VALUE;
        }
        if(dp[n] != Integer.MAX_VALUE){
            return dp[n];
        }

        int min = Math.min(execute(n - 3), execute(n - 5));


        if(min == Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        dp[n] = min + 1;

        return dp[n];
    }

}
