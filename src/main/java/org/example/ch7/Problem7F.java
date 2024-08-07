package org.example.ch7;

import java.io.*;
import java.util.Arrays;


public class Problem7F {

    static int[] dp;
    static int[] track;

    private static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(br.readLine());
            dp = new int[n + 1];
            track = new int[n + 1];


            Arrays.fill(dp, INF);
            dp[1] = 0;
            track[1] = -1;


            for(int i = 2; i <= n; i++){
                int minIdx = i - 1;

                if(i % 2 == 0 && dp[i / 2] < dp[minIdx]){
                    minIdx = i / 2;
                }
                if(i % 3 == 0 && dp[i / 3] < dp[minIdx]){
                    minIdx = i / 3;
                }

                dp[i] = 1 + dp[minIdx];
                track[i] = minIdx;
            }

            bw.write(dp[n] + "\n");
            bw.write(createResultStr(n));
            bw.flush();
        }
    }

    private static String createResultStr(int n){
        StringBuilder sb = new StringBuilder();
        while(n != -1){
            sb.append(n).append(' ');
            n = track[n];
        }

        return sb.toString();
    }
}