package org.example.ch6;

import java.io.*;
import java.util.*;

public class Problem6A{

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int peopleCount = Integer.parseInt(st.nextToken());
            int colorCount = Integer.parseInt(st.nextToken());

            int[] colors = new int[colorCount];

            int r = 0;
            for(int i = 0; i < colorCount; i++){
                colors[i] = Integer.parseInt(br.readLine());
                r = Math.max(r, colors[i]);
            }

            bw.write(Integer.toString(execute(peopleCount, colors, 1, r)));
            bw.flush();
        }
    }

    static int execute(int peopleCount, int[] colors, int l, int r){
        int result = 1;

        while(l < r){
            int mid = (l + r) / 2;
            if(!check(peopleCount, mid, colors)){
                l = mid + 1;
                continue;
            }
                r = mid;
                result = mid;
        }


        return result;
    }

    static boolean check(int peopleCount, int mid, int[] colors){
        long num = 0;
        for(int i = 0; i < colors.length; i++){
            num += colors[i] / mid;
            if(colors[i] % mid != 0){
                num++;
            }

        }

        return peopleCount >= num;
    }


}

