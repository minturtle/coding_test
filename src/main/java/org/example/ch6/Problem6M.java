package org.example.ch6;

import java.io.*;
import java.util.*;
public class Problem6M {

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }


            bw.write(Integer.toString(execute(arr)));
        }
    }

    static int execute(int[] arr){
        int[] cnt = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            int prevMax = 0;

            for(int j = 0; j < i; j++){
                if(arr[i] <= arr[j]){
                    continue;
                }

                prevMax = Math.max(prevMax, cnt[j]);
            }
            cnt[i] = prevMax + 1;
        }

        return Arrays.stream(cnt).max().orElseGet(()->1);
    }
}
