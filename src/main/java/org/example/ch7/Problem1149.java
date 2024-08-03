package org.example.ch7;

import java.io.*;
import java.util.StringTokenizer;

public class Problem1149 {

    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            int[][] cost = new int[n][3];

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 3; j++){
                    cost[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(Integer.toString(execute(cost)));
            bw.flush();
        }
    }

    static int execute(int[][] cost){
        int[][] dp = new int[cost.length][3];

        dp[0][RED] = cost[0][RED];
        dp[0][GREEN] = cost[0][GREEN];
        dp[0][BLUE] = cost[0][BLUE];

        for(int i = 1; i < cost.length; i++){
            dp[i][RED] = Math.min(dp[i-1][GREEN], dp[i-1][BLUE]) + cost[i][RED];
            dp[i][GREEN] = Math.min(dp[i-1][RED], dp[i-1][BLUE]) + cost[i][GREEN];
            dp[i][BLUE] = Math.min(dp[i-1][RED], dp[i-1][GREEN]) + cost[i][BLUE];
        }

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < 3; i++){
            min = Math.min(min, dp[cost.length - 1][i]);
        }

        return min;
    }

}
