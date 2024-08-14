package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7T {

    private static int[] numbers;

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            numbers = new int[n];
            dp = new long[n][900];

            for(int i = 0; i < n; i++){
                Arrays.fill(dp[i], -1);
            }

            for(int i = 0; i < n; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(Long.toString(execute(1, numbers[0])));
            bw.flush();
        }
    }

    private static long execute(int idx, int n){
        if(idx == numbers.length - 1){
            return n == numbers[idx] ? 1 : 0;
        }
        if(dp[idx][n] != -1){
            return dp[idx][n];
        }

        dp[idx][n] = 0;
        if(n + numbers[idx] <= 20){
            dp[idx][n] += execute(idx + 1, n + numbers[idx]);
        }
        if(n - numbers[idx] >= 0){
            dp[idx][n] += execute(idx + 1, n - numbers[idx]);
        }

        return dp[idx][n];
    }
}
