package org.example.ch6;

import java.io.*;
import java.util.*;


public class Problem6B {


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] numbers = new int[n];
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(Integer.toString(execute(numbers, m)));
            bw.flush();
        }
    }

    static int execute(int[] numbers, int m){
        int l = 0, r = Arrays.stream(numbers).sum() + 1;
        int min = Integer.MAX_VALUE;

        while(l < r){
            int mid = (l + r) / 2;
            if(!check(mid, m, numbers)){
                l = mid + 1;
                continue;
            }
            min = mid;
            r = mid;
        }

        return min;
    }


    static boolean check(int size, int m, int[] numbers){
        int pt = 0;
        int pt2 = 0;
        int[] sumList = new int[m];

        while(pt2 < numbers.length){
            if(pt >= m){
                return false;
            }

            if(sumList[pt] + numbers[pt2] > size){
                pt++;
                continue;
            }
            sumList[pt] += numbers[pt2];
            pt2++;
        }
        return true;
    }
}
