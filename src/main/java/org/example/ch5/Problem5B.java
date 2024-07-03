package org.example.ch5;

import java.io.*;
import java.util.*;

public class Problem5B {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

            String st = br.readLine().trim();
            String boomSt = br.readLine().trim();


            Optional<String> result = execute(st, boomSt);


            bw.write(result.orElseGet(()->"FRULA"));
            bw.flush();

        }
    }


    static Optional<String> execute(String str, String boomStr){
        Deque<Character> deque = new LinkedList<>();
        char boomLastChar = boomStr.charAt(boomStr.length() - 1);


        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            deque.addLast(c);

            if(c == boomLastChar){
                removeBoomStrIfExists(deque, boomStr);
            }

        }

        if(deque.isEmpty()){
            return Optional.empty();
        }

        StringBuilder sb = new StringBuilder();

        int resultSize = deque.size();
        for(int i = 0; i < resultSize; i++){
            sb.append(deque.removeFirst());
        }

        return Optional.of(sb.toString());
    }

    static void removeBoomStrIfExists(Deque<Character> stack, String boomStr){
        if(stack.size() < boomStr.length()){
            return;
        }

        Stack<Character> tmp = new Stack<>();

        for(int i = boomStr.length() - 1; i >= 0; i--){
            char c = boomStr.charAt(i);
            char c1 = stack.removeLast();

            tmp.push(c1);

            // boomStr이 아니면 원복
            if(c != c1){
                while(!tmp.isEmpty()){
                    stack.addLast(tmp.pop());
                }
                return;
            }
        }



    }

}
