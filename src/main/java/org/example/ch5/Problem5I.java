package org.example.ch5;

import java.io.*;
import java.util.*;

public class Problem5I {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());

            if(n == 1){
                bw.write('0');
                bw.flush();
                return;
            }


            int[] numbers = new int[n];



            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n ; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            int k = Integer.parseInt(br.readLine());

            bw.write(Long.toString(execute(numbers, k)));
            bw.flush();
        }
    }

    static long execute(int[] numbers, int k){
        long result = 0;

        Arrays.sort(numbers);


        int p1 = 0, p2 = numbers.length - 1;

        while(p1 < p2) {

            int sum = numbers[p1] + numbers[p2];

            if(sum == k){
                result++;
                p1++;
                p2--;
                continue;
            }
            if(sum > k){
                p2--;
                continue;
            }
            p1++;
        }

        return result;
    }

}
