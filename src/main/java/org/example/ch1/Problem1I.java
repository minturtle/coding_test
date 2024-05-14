package org.example.ch1;

import java.util.*;
import java.io.*;


public class Problem1I {

    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int resultLength = nk[0] - nk[1] + 1;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < resultLength; i++){
            int sum = 0;
            for(int j = i; j < i + nk[1]; j++){
                sum += input[j];
            }
            max = max < sum ? sum : max;
        }


        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();

    }


}
