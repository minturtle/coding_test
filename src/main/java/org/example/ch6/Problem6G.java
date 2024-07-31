package org.example.ch6;

import java.io.*;
import java.util.*;

public class Problem6G {


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = (y*100) / x;

            long min = Long.MAX_VALUE;
            long l = 0;
            long r = Integer.MAX_VALUE / 2;

            while(l <= r){
                long mid = (l + r) / 2;
                long newZ = ((y + mid)*100)/ (x + mid);

                if(newZ == z){
                    l = mid + 1;
                    continue;
                }
                min = Math.min(min, mid);
                r = mid - 1;
            }


            if(min == Long.MAX_VALUE){
                bw.write("-1\n");
            }
            else{
                bw.write(min + "\n");
            }
            bw.flush();
        }
    }
}
