package org.example.ch7;

import java.io.*;

public class Problem1003 {

    static int[] dp0;
    static int[] dp1;

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
                max = Math.max(max, inputs[i]);
            }

            dp0 = new int[max + 1];
            dp1 = new int[max + 1];
            execute(max);

            for(int input : inputs){
                bw.write(String.format("%d %d\n", dp0[input], dp1[input]));
            }
            bw.flush();
        }
    }

    static void execute(int n){
        if(n == 0){
            dp0[0] = 1;
            return;
        }

        if(n == 1){
            dp1[1] = 1;
            return;
        }

        dp0[0] = 1;
        dp1[1] = 1;


        for(int i = 2; i <= n; i++){
            dp0[i] = dp0[i -1] + dp0[i - 2];
            dp1[i] = dp1[i - 1] + dp1[i - 2];
        }

    }

}
