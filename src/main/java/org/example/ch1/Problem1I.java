package org.example.ch1;

import java.util.*;
import java.io.*;


public class Problem1I {

    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] prefixSum = getPrefixSum(input);

        int max = prefixSum[nk[1] - 1]; // 가상의 result 배열 값 중 첫번째 값을 max로 초기화

        for(int i = nk[1]; i < prefixSum.length; i++){
            int value = prefixSum[i] - prefixSum[i - nk[1]];
            max = max < value ? value : max;
        }

        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();


    }

    public static int[] getPrefixSum(int[] input){
        int[] result = new int[input.length];

        result[0] = input[0];

        for(int i = 1; i < result.length; i++){
            result[i] = input[i] + result[i -1];
        }

        return result;
    }
}
