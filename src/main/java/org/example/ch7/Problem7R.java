package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7R {

    private static int[] numbers;
    private static int[][] dp;

    public static void main(String[] args) throws IOException{
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            int n = Integer.parseInt(br.readLine());
            numbers = new int[n + 1];
            dp = new int[n + 1][n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= n; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());

            for(int i = 1; i <= n; i++){
                dp[i][i] = 1;
            }
            for(int i = 1; i < n; i++){
                if(numbers[i] != numbers[i + 1]){
                    continue;
                }
                dp[i][i + 1] = 1;
            }
            for(int size = 2; size <= n; size++){
                for(int i = 1; i <= n - size; i++){
                    if(numbers[i] != numbers[i + size]){
                        continue;
                    }
                    dp[i][i + size] = dp[i + 1][i + size -1];
                }

            }


            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                bw.write(dp[s][e] + "\n");
            }
            bw.flush();
        }
    }


}
