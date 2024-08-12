package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7R {

    private static int[] numbers;
    private static int[][] dp;

    private static final int TRUE = 1;
    private static final int FALSE = 0;

    public static void main(String[] args) throws IOException{
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            int n = Integer.parseInt(br.readLine());
            numbers = new int[n + 1];
            dp = new int[2001][2001];

            for(int i = 0; i < dp.length; i++){
                Arrays.fill(dp[i], -1);
            }

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= n; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                bw.write(isPell(s, e) + "\n");
                bw.flush();
            }
        }
    }

    private static int isPell(int s, int e){
        if(s >= e){
            return TRUE;
        }
        if(dp[s][e] != -1){
            return dp[s][e];
        }
        if(numbers[s] != numbers[e]){
            dp[s][e] = FALSE;
            return FALSE;
        }
        dp[s][e] = isPell(s + 1, e - 1);

        return dp[s][e];
    }

}
