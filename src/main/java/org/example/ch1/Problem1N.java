package org.example.ch1;

import java.io.*;
import java.util.*;

public class Problem1N {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] abc = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        long result = get(abc[0], abc[1], abc[2]);

        bw.write(Long.toString(result));
        bw.flush();

        br.close();
        bw.close();


    }

    // (A * B) % C = (A % C) * (B % C) % C
    // (A * B * C) % D = (((A*B) % D) * (C % D)) % D

    public static long get(int a, int b, int c){
        if(b == 1){
            return a;
        }

        int b1 = b / 2;

        long halfResult = get(a, b1, c) % c;

        // b가 홀수인 경우는 a를 한번 더 곱해줘야 함.
        if(b1 == 1){
            return (((halfResult * halfResult) % c) * (a % c) % c);
        }

        return (halfResult * halfResult) % c;
    }


}
