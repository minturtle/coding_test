package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7L {


    private static int[] joy;
    private static int[] loss;

    private static int[][] dp;

    private static int n;
    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            n = Integer.parseInt(br.readLine());
            joy = new int[n];
            loss = new int[n];


            init(br.readLine(), loss);
            init(br.readLine(), joy);

            dp = new int[n][101];
            for(int i = 0; i < n; i++){
                Arrays.fill(dp[i], -1);
            }

            bw.write(Integer.toString(dfs(0, 100)));
            bw.flush();
        }
    }

    private static int dfs(int idx, int hp){
        if(hp <= 0){
            return Integer.MIN_VALUE;
        }
        if(dp[idx][hp] != -1){
            return dp[idx][hp];
        }
        if(idx == n-1){
            dp[idx][hp] = hp > loss[idx] ? joy[idx] : 0;
            return dp[idx][hp];
        }

        dp[idx][hp] = Math.max(dfs(idx + 1, hp), dfs(idx + 1, hp - loss[idx]) + joy[idx]);
        return dp[idx][hp];
    }


    private static void init(String s, int[] arr){
        StringTokenizer st = new StringTokenizer(s);
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

}
