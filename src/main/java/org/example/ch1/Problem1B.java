package org.example.ch1;


import java.util.*;
import java.io.*;

public class Problem1B {

    public static void main(String ... args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String input = br.readLine().trim();

       Alpha a = new Alpha();


       for(int i = 0; i < input.length(); i++){
           a.add(input.charAt(i));
       }


       a.print();

    }


    private static class Alpha{

        private int[] count = new int[26];


        public void add(char c){
            count[c - 'a']++;
        }

        public void print(){
            for(int i = 0; i < 25; i++){
                System.out.print(count[i] + " ");
            }
            System.out.println(count[25]);

        }

    }

}
