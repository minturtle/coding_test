package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7G {

    private static Set<Integer> coins;
    private static final int INF = (int)1e9;

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            coins = new HashSet<>(n);

            dp = new int[k + 1];
            Arrays.fill(dp, INF);
            dp[0] = 0;

            for(int i = 0; i < n; i++){
                coins.add(Integer.valueOf(br.readLine()));
            }

            execute(k);
            bw.write(Integer.toString(dp[k] == INF? -1 : dp[k]));
            bw.flush();
        }
    }

    static void execute(int k){
        for(int i = 1; i <= k; i++){
            int min = INF;
            for(int coin : coins){
                if(i - coin < 0){
                    continue;
                }
                min = Math.min(min, dp[i - coin]);
            }
            if(min == INF){
                continue;
            }
            dp[i] = min + 1;
        }
    }

}
