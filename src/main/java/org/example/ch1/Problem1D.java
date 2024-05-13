package org.example.ch1;


import java.io.*;
import java.util.*;

public class Problem1D {

    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine().trim();


        boolean result = checkPel(input);

        bw.write(result ? "1" : "0");
        bw.flush();

        br.close();
        bw.close();
    }

    public static boolean checkPel(String word){
        int mid = Math.floorDiv(word.length(), 2);

        for(int i = 0; i < mid + 1; i++){
            if(word.charAt(i) != word.charAt(word.length() - i - 1)){
                return false;
            }
        }

        return true;
    }


}
