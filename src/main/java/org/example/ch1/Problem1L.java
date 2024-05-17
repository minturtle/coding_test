package org.example.ch1;

import java.io.*;
import java.util.*;

public class Problem1L {

    static int[] numberCountArr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine().trim());
        int m = Integer.parseInt(br.readLine().trim());
        int[] input = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        numberCountArr = new int[100001];

        for(int i = 0; i < n; i ++){
            numberCountArr[input[i]]++;
        }

        int result = 0;

        for(int number : input){
            int pairNumber = m-number;

            if(isPairNumberExists(number, pairNumber)){
                continue;
            }

            result++;
            numberCountArr[number]--;
            numberCountArr[pairNumber]--;
        }


        bw.write(Integer.toString(result));
        bw.flush();

        br.close();
        bw.close();

    }

    static boolean isPairNumberExists(int number, int pairNumber){
        return pairNumber < 0 || pairNumber > 100000 || number == pairNumber || numberCountArr[number] < 1 || numberCountArr[pairNumber] < 1;
    }

}
