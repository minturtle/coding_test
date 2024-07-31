package org.example.ch6;

import java.io.*;
import java.util.*;

public class Problem6H {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int t = Integer.parseInt(br.readLine());

            for(int i = 0; i < t; i++){
                int n = Integer.parseInt(br.readLine());
                int[] numbers1 = new int[n];
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int j = 0; j < n; j++){
                    numbers1[j] = Integer.parseInt(st.nextToken());
                }

                int m = Integer.parseInt(br.readLine());
                int[] numbers2 = new int[m];
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < m; j++){
                    numbers2[j] = Integer.parseInt(st.nextToken());
                }

                bw.write(execute(numbers1, numbers2));
            }

            bw.flush();
        }
    }

    static String execute(int[] numbers1, int[] numbers2){
        Arrays.sort(numbers1);
        StringBuilder result = new StringBuilder();

        for(int number : numbers2){
            if(Arrays.binarySearch(numbers1, number) >= 0){
                result.append("1\n");
                continue;
            }
            result.append("0\n");
        }

        return result.toString();
    }
}
