package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5H {

    static int[] visited = new int[100001];

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

            int n = Integer.parseInt(br.readLine());
            int[] input = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                input[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(Long.toString(getCnt(input)));
            bw.flush();
        }
    }


    static long getCnt(int[] numbers){
        long result = 0;

        visited[numbers[0]] = 1;

        for(int p1 = 0, p2 = 1; p1 < numbers.length; p1++){
            p2 = extendEnd(p2, numbers);
            for(int i = 0; i < p2 - p1; i++){
                result++;
            }

            visited[numbers[p1]]--;
        }

        return result;
    }


    // start~ end번까지의 숫자가 중복되지 않는 가장 큰 end를 리턴
    static int extendEnd(int start, int[] numbers){

        for(int i = start; i < numbers.length; i++){
            if(visited[numbers[i]] != 0){
                return i;
            }

            visited[numbers[i]]++;
        }

        return numbers.length;
    }
}
