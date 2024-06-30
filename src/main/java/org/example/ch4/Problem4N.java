package org.example.ch4;

import java.io.*;
import java.util.*;


public class Problem4N {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            String s = br.readLine().trim();
            Queue<Character> inputQueue = new LinkedList<>();

            for(int i = 0; i < s.length(); i++){
                inputQueue.add(s.charAt(i));
            }

            bw.write(execute(inputQueue) ? "YES" : "NO");

            bw.flush();
        }
    }

    static boolean execute(Queue<Character> inputQueue){

        while(!inputQueue.isEmpty()){
            Character c1;
            Character c2;
            Character c3;

            switch(inputQueue.peek()){
                case 'p':
                    if(inputQueue.size() < 2){
                        return false;
                    }
                    c1 = inputQueue.poll();
                    c2 = inputQueue.poll();

                    if(!(c1 == 'p') || !(c2 == 'i')){
                        return false;
                    }
                    break;
                case 'k':
                    if(inputQueue.size() < 2){
                        return false;
                    }

                    c1 = inputQueue.poll();
                    c2 = inputQueue.poll();

                    if(!(c1 == 'k') || !(c2 == 'a')){
                        return false;
                    }
                    break;
                case 'c':
                    if(inputQueue.size() < 3){
                        return false;
                    }

                    c1 = inputQueue.poll();
                    c2 = inputQueue.poll();
                    c3 = inputQueue.poll();


                    if(!(c1 == 'c') || !(c2 == 'h')|| !(c3 == 'u')){
                        return false;
                    }

                    break;
                default:
                    return false;
            }



        }

        return true;

    }
}
