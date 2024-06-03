package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2T {

    static int n;
    static int[] input;
    static int[] result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        input = new int[n];
        result = new int[n];

        for(int i = 0; i < n; i++){
            input[i] = Integer.parseInt(st.nextToken());

        }


        for(int i = 0; i < n; i++){
            bw.write(Integer.toString(result[i]));
            bw.write(' ');
        }
        bw.flush();

        br.close();
        bw.close();
    }


}
