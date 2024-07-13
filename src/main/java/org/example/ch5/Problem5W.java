package org.example.ch5;

import java.io.*;
import java.util.*;

public class Problem5W {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            int[] numbers = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(Integer.toString(execute(numbers)));

        }
    }

    static int execute(int[] numbers){
        int tmp = 0;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < numbers.length; i++){
            int number = numbers[i];
            tmp += number;
            max = Math.max(tmp, max);

            if(i == numbers.length - 1){
                break;
            }
            tmp = Math.max(tmp, 0);
        }

        return max;
    }

}
