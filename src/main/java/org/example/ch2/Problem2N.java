package org.example.ch2;

import java.io.*;
import java.util.*;

public class Problem2N {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < n; i++){
            String input = br.readLine().trim();
            boolean testResult = doTest(input);

            bw.write(testResult ? "YES" : "NO");
            bw.newLine();
        }

        bw.flush();

        br.close();
        bw.close();

    }

    static boolean doTest(String input){
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);

            if(stack.isEmpty()){
                stack.push(c);
                continue;
            }
            if(stack.peek() == '(' && c == ')'){
                stack.pop();
                continue;
            }

            stack.push(c);
        }

        return stack.isEmpty();
    }

}
