package org.example.ch1;

import java.util.*;
import java.io.*;


public class Problem1E {


    static int[] alphabetCount = new int[26];

    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < count; i++){
            char[] name = br.readLine().trim().toCharArray();
            alphabetCount[name[0] - 'a']++;
        }

        boolean isPredaja = true;

        for(int i = 0; i < 26; i++){
            if(alphabetCount[i] < 5){
                continue;
            }
            bw.write((char)(i + 'a'));
            isPredaja = false;
        }

        if(isPredaja){
            bw.write("PREDAJA");
        }

        bw.flush();
        bw.close();
        br.close();




    }

}
