package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7P {

    private static int maxA;
    private static int maxB;

    private static int destA;

    private static int destB;
    private static int[][] dp;
    private static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            maxA = Integer.parseInt(st.nextToken());
            maxB = Integer.parseInt(st.nextToken());
            dp = new int[maxA + 1][maxB + 1];
            visited = new boolean[maxA + 1][maxB + 1];

            for(int a = 0; a < maxA + 1; a++){
                Arrays.fill(dp[a], Integer.MAX_VALUE);
            }


            destA = Integer.parseInt(st.nextToken());
            destB = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(execute(0, 0)));

        }

    }


    private static int execute(int a, int b){
        if(destA == a && destB == b){
            return 0;
        }
        if(dp[a][b] != Integer.MAX_VALUE){
            return dp[a][b];
        }
        if(visited[a][b]){
            return -1;
        }


        int moveToBAmount = Math.min((maxB - b), a);
        int moveToAAmount = Math.min((maxA - a), b);

        int[][] args = {{maxA, b}, {a, maxB},
                {0, b}, {a, 0},
                {a - moveToBAmount, b + moveToBAmount}, {a + moveToAAmount, b - moveToAAmount}
        };
        visited[a][b] = true;
        for(int[] arg : args){

            int tmp = execute(arg[0], arg[1]);

            if(tmp == -1){
                continue;
            }

            dp[a][b] = Math.min(dp[a][b], tmp + 1);
        }

        if(dp[a][b] == Integer.MAX_VALUE){
            dp[a][b] = -1;
        }


        visited[a][b] = false;
        return dp[a][b];
    }
}
