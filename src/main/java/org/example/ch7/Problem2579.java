package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem2579 {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());

            int[] scores = new int[n + 1];
            for(int i = 0; i < n; i++){
                scores[i] = Integer.parseInt(br.readLine());
            }

            bw.write(Integer.toString(execute(scores, n)));
            bw.flush();
        }
    }

    static int execute(int[] scores, int n){
        int[] dp = new int[scores.length];
        dp[0] = scores[0];
        dp[1] = scores[0] + scores[1];
        dp[2] = Math.max(scores[0] + scores[2], scores[1] + scores[2]);

        for(int i = 3; i < n; i++){
            dp[i] = Math.max(dp[i -2] + scores[i], scores[i -1] + scores[i] + dp[i -3]);
        }

        return dp[n - 1];
    }

}
