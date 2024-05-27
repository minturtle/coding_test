package org.example.ch2;


import java.io.*;
import java.util.*;

public class Problem2K {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < n ; i++){
            int input = Integer.parseInt(br.readLine().trim());
            int result = getRightZeroCount(input);

            bw.write(Integer.toString(result));
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }


    static int getRightZeroCount(int number){

        int fiveCount = 0;

        for(int i = 1; Math.pow(5, i) <= number; i++){
            fiveCount += number / Math.pow(5, i);
        }


        return fiveCount;

    }

}
