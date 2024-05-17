package org.example.ch1;


import java.io.*;
import java.util.*;

public class Problem1M {


    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());

        int result = 0;

        for(int i = 0; i < n; i++){
            String input = br.readLine().trim();
            if(isGoodWord(input)){
                result++;
            }
        }



        bw.write(Integer.toString(result));
        bw.flush();

        br.close();
        bw.close();

    }



    public static boolean isGoodWord(String str){

        char[] cArr = str.toCharArray();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < cArr.length; i++){
            if(stack.isEmpty()){
                stack.push(cArr[i]);
                continue;
            }
            if(stack.peek() == cArr[i]){
                stack.pop();
                continue;
            }
            stack.push(cArr[i]);
        }

        return stack.isEmpty();
    }


}



