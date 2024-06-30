package org.example.ch4;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Problem4O {

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            br.readLine();

            String s = br.readLine().trim();
            boolean[] result = execute(s);

            int max = Integer.MIN_VALUE;
            int cnt = 0;

            for(int i = 0; i < result.length; i++){
                if(result[i]){
                    cnt++;
                    continue;
                }
                max = Math.max(cnt, max);
                cnt = 0;
            }

            max = Math.max(cnt, max);

            bw.write(Integer.toString(max));
            bw.flush();
        }


    }

    static boolean[] execute(String s){
        int size = s.length();

        boolean[] result = new boolean[size];

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < size; i++){
            if(stack.isEmpty()){
                stack.push(i);
                continue;
            }

            char c = s.charAt(i);

            if(c == '('){
                stack.push(i);
                continue;
            }

            if(s.charAt(stack.peek()) == '('){
                Integer prefix = stack.pop();

                result[prefix] = true;
                result[i] = true;
                continue;
            }

            stack.push(i);
        }


        return result;
    }

}
