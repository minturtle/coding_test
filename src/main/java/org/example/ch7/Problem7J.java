package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7J {

    private static Thing[] things;
    private static int[][] dp;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            things = new Thing[n];
            dp = new int[k + 1][n];

            for(int i = 0; i <= k; i++){
                Arrays.fill(dp[i], -1);
            }
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                things[i] = new Thing(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            bw.write(Integer.toString(dfs(k, n - 1)));
            bw.flush();
        }
    }

    private static int dfs(int k, int i){
        if(i < 0){
            return 0;
        }
        if(dp[k][i] != -1){
            return dp[k][i];
        }

        Thing thing = things[i];

        dp[k][i] = dfs(k, i-1);

        if(thing.weight > k){
            return dp[k][i];
        }

        dp[k][i] =  Math.max(dp[k][i], dfs(k - thing.weight, i-1) + thing.value);
        return dp[k][i];
    }


    private static class Thing{
        public final int weight;
        public final int value;

        public Thing(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

}
