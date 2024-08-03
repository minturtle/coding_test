package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem9095 {

    static int[] dp;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

            int t = Integer.parseInt(br.readLine());
            int[] inputs = new int[t];
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < t; i++){
                inputs[i] = Integer.parseInt(br.readLine());
                max = Math.max(inputs[i], max);
            }
            dp = new int[max + 1];

            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            execute(max);

            for(int input : inputs){
                bw.write(dp[input] + "\n");
            }
            bw.flush();
        }
    }

    static void execute(int max){
        for(int i = 4; i <= max; i++){
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
    }

}
