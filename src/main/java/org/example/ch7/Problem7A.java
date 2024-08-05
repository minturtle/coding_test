package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7A {

    public static final int START_POINT = 0;
    public static final int INF = (int)1e9;

    static int[][] adjArr;
    static int n;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            n = Integer.parseInt(br.readLine());
            adjArr = new int[n][n];
            dp = new int[n][(1 << n)];

            for(int i = 0; i < n; i++){
                Arrays.fill(dp[i], -1);
            }


            for(int i = 0; i< n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    adjArr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(Integer.toString(dfs(START_POINT, 1)));

        }
    }


    static int dfs(int point, int visited){
        if(isAllVisited(visited)){
            if(adjArr[point][START_POINT] == 0){
                return INF;
            }

            return adjArr[point][START_POINT];
        }

        if(dp[point][visited] != -1){
            return dp[point][visited];
        }


        dp[point][visited] = INF;

        for(int i = 0; i < n; i++){
            if(adjArr[point][i] == 0 || getBit(visited, i)){
                continue;
            }
            int tmp = setBit(visited, true, i);
            dp[point][visited] = Math.min(dp[point][visited],  dfs(i, tmp) + adjArr[point][i]);

        }

        return dp[point][visited];
    }

    static boolean isAllVisited(int visited){
        int checkBit = (1 << n) - 1;
        return (visited & checkBit) == checkBit;
    }


    static int setBit(int bits, boolean value, int idx){
        if(value){
            return bits | (1 << idx);
        }
        return bits | ~(1 << idx);
    }

    static boolean getBit(int bits, int idx){
        return (bits & (1 << idx)) != 0;
    }

}
