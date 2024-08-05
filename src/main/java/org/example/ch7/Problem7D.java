package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7D {


    static int sec;
    static int maxMoveCnt;

    static int[] jaduMap;

    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            sec = Integer.parseInt(st.nextToken());
            maxMoveCnt = Integer.parseInt(st.nextToken());

            jaduMap = new int[sec];
            dp = new int[2][sec][maxMoveCnt + 1];
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < sec; j++){
                    Arrays.fill(dp[i][j], -1);
                }
            }

            for(int i = 0; i < sec; i++){
                jaduMap[i] = Integer.parseInt(br.readLine()) - 1;
            }

            bw.write(Integer.toString(dfs(0, 0, 0)));
        }
    }

    static int dfs(int currentPos, int moveCount, int currentSec){

        if(dp[currentPos][currentSec][moveCount] != -1){
            return dp[currentPos][currentSec][moveCount];
        }

        if(currentSec == sec-1){
            if(moveCount < maxMoveCnt){
                return 1;
            }
            return jaduMap[currentSec] == currentPos ? 1 : 0;
        }

        dp[currentPos][currentSec][moveCount] = 0;

        if(moveCount < maxMoveCnt){
            int nextPos = (currentPos + 1) % 2;
            int current = jaduMap[currentSec] == nextPos ? 1 : 0;

            dp[currentPos][currentSec][moveCount] = current + dfs(nextPos, moveCount + 1, currentSec + 1);
        }


        int current = jaduMap[currentSec] == currentPos ? 1 : 0;

        dp[currentPos][currentSec][moveCount] =  Math.max(
                dp[currentPos][currentSec][moveCount],
                current + dfs(currentPos, moveCount, currentSec+1)
        );

        return dp[currentPos][currentSec][moveCount];
    }


}
