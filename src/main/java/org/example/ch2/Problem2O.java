package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2O {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            String input = br.readLine();
            if(input.charAt(0) == '.'){
                break;
            }

            boolean result = doTest(input);

            bw.write(result ? "yes" : "no");
            bw.newLine();

        }

        bw.flush();
        br.close();
        bw.close();
    }

    static boolean doTest(String str){
        Stack<Character> stack = new Stack<>();

        // str.length() -1인 이유는 '.'을 제외하기 위함.
        for(int i = 0; i < str.length() - 1; i++){
            char c = str.charAt(i);

            if(!isGwal(c)){
                continue;
            }

            if(stack.isEmpty()){
                stack.push(c);
                continue;
            }

            if(isPair(stack.peek(), c)){
                stack.pop();
            }
            else{
                stack.push(c);
            }

        }


        return stack.isEmpty();
    }

    // 괄호인지 확인하는 메서드
    static boolean isGwal(char c){
        return c =='(' || c == ')' || c == '[' || c == ']';
    }

    static boolean isPair(char c1, char c2){
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']');
    }

}
