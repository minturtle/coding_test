package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem1463 {

    static int[] dp;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());

            dp = new int[n + 1];

            bw.write(Integer.toString(execute(n)));
            bw.flush();
        }
    }

    static int execute(int n) {
        if(n < 2){
            return 0;
        }
        if(dp[n] != 0){
            return dp[n];
        }

        int min = Integer.MAX_VALUE;

        if(n % 3  == 0){
            min = Math.min(min, execute(n / 3));
        }

        if(n % 2 == 0){
            min = Math.min(min, execute(n / 2));
        }

        min = Math.min(min, execute(n - 1));

        dp[n] = min + 1;
        return dp[n];
    }

}
