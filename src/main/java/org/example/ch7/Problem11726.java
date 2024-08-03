package org.example.ch7;

import java.io.*;

public class Problem11726 {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            if(n <= 2){
                bw.write(Integer.toString(n));
                bw.flush();
                return;
            }

            dp = new int[n + 1];
            execute(n);

            bw.write(Integer.toString(dp[n]));
            bw.flush();
        }
    }

    static void execute(int n){
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }
    }

}
