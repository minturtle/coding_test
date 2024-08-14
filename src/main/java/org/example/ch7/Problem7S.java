package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7S {

    private static String str;
    private static boolean[][] isPell;
    private static int[] dp;


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            str = br.readLine().trim();

            isPell = new boolean[str.length()][str.length()];
            dp = new int[str.length()];

            createPell();
            createDp();

            bw.write(Integer.toString(dp[str.length() - 1]));
            bw.flush();
        }
    }

    private static void createPell(){
        for(int i = 0; i < isPell.length; i++){
            isPell[i][i] = true;
        }
        for(int i = 0; i < isPell.length - 1; i++){
            if(str.charAt(i) != str.charAt(i + 1)){
                continue;
            }
            isPell[i][i + 1] = true;
        }
        for(int size = 3; size <= str.length(); size++){
            for(int i = 0; i + size <= isPell.length; i++){
                if(str.charAt(i) != str.charAt(i + size - 1)){
                    continue;
                }
                if(!isPell[i + 1][i + size - 2]){
                    continue;
                }
                isPell[i][i + size - 1] = true;
            }
        }
    }

    private static void createDp(){
        for(int i = 0; i < dp.length; i++){
            if(isPell[0][i]){
                dp[i] = 1;
                continue;
            }
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j < i; j++){
                if(!isPell[j + 1][i]){
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
    }

}
