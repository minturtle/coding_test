package org.example.ch7;

import java.io.*;
import java.util.Arrays;

public class Problem7E {

    static long[][] dp = new long[61][31];

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            setDp();
            while(true){
                int n = Integer.parseInt(br.readLine());

                if(n == 0){
                    break;
                }

                bw.write(dp[0][n] + "\n");
            }
            bw.flush();
        }
    }

    static void setDp(){
        for(int i = 0; i < 61; i++){
            Arrays.fill(dp[i], -1);
            dp[i][0] = 1;
        }

        dfs(0, 30);
    }

    static long dfs(int halfCnt, int cnt){
        if(halfCnt == 0 && cnt == 0){
            return 0;
        }
        if(dp[halfCnt][cnt] != -1){
            return dp[halfCnt][cnt];
        }

        dp[halfCnt][cnt] = 0;
        dp[halfCnt][cnt] += dfs(halfCnt + 1, cnt - 1);

        if(halfCnt > 0){
            dp[halfCnt][cnt] += dfs(halfCnt-1, cnt);
        }

        return dp[halfCnt][cnt];
    }
}
