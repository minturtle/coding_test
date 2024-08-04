package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem11727 {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            if(n == 1){
                bw.write("1");
                bw.flush();
                return;
            }

            bw.write(Integer.toString(execute(n)));

        }
    }

    static int execute(int n){
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 3;

        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i-1] + (dp[i -2]) * 2) % 10007;
        }

        return dp[n];
    }


}
