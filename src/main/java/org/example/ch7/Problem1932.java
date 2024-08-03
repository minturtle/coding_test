package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem1932 {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            int n = Integer.parseInt(br.readLine());
            if(n == 1){
                bw.write(br.readLine());
                bw.flush();
                return;
            }


            int[][] triangle = new int[n][n];

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < i + 1; j++){
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[n][n];

            for(int i = 0; i <n; i++){
                Arrays.fill(dp[i], -1);
            }

            for(int i = 0; i < n; i++){
                dp[n-1][i] = triangle[n-1][i];
            }


            bw.write(Integer.toString(dfs(triangle, 0, 0)));
        }
    }

    static int dfs(int[][] triangle, int x, int y){
        if(dp[y][x] != -1){
            return dp[y][x];
        }
        int maxVal = Math.max(dfs(triangle, x, y + 1), dfs(triangle, x + 1, y + 1)) + triangle[y][x];
        dp[y][x] = maxVal;

        return maxVal;

    }


}
