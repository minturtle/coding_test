package org.example.ch6;

import java.io.*;
import java.util.*;

public class Problem6C {

    private static long UPPER_BOUND = 0;
    private static long LOWER_BOUND = 0;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());


            int[] days = new int[n];
            for(int i = 0; i < n; i++){
                days[i] = Integer.parseInt(br.readLine());
                UPPER_BOUND += days[i];
                LOWER_BOUND = Math.max(LOWER_BOUND, days[i]);
            }
            
            bw.write(Long.toString(execute(days, m)));
            bw.flush();
        }
    }

    private static long execute(int[] days, int count){
        long min = Integer.MAX_VALUE;
        long l = LOWER_BOUND, r = UPPER_BOUND;
        
        while(l < r){
            long mid = (l + r) / 2;
            
            if(LOWER_BOUND > mid || getCount(days, mid) > count){
                l = mid + 1;
                continue;
            }
            
            r = mid;
            min = Math.min(mid, min);
        }

        return min;
    }

    private static int getCount(int[] days, long k){
        long pocket = 0;
        int cnt = 0;

        for(int day : days){
            if(pocket < day){   
                pocket = k;
                cnt++;
            }
            pocket -= day;
        }
        return cnt;
    }

}
