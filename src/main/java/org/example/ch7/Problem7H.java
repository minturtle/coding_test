package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7H {

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] coins = new int[n];

            for(int i = 0; i < n; i++){
                coins[i] = Integer.parseInt(br.readLine());
            }

            int[] dp = new int[k + 1];
            execute(dp, coins, k);

            bw.write(Integer.toString(dp[k]));
            bw.flush();
        }
    }

    static void execute(int[] dp, int[] coins, int k){
        dp[0] = 1;

        for(int coin : coins){
            for(int i = coin; i <= k; i++){
                dp[i] += dp[i - coin];
            }
        }

    }

}

// 111 12
// 1111 112 22
