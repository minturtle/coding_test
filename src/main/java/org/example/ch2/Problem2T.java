package org.example.ch2;

import java.io.*;
import java.util.*;

public class Problem2T {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] input = new int[n];
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();


        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for(int i = 0; i < n; i++){
            input[i] = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty() && input[stack.peek()] < input[i]){
                result[stack.pop()] = input[i];
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){
            Integer idx = stack.pop();
            result[idx] = -1;
        }

        for(int num : result){
            bw.write(Integer.toString(num));
            bw.write(' ');
        }

        bw.newLine();


        bw.flush();
        br.close();
        bw.close();

    }




}
