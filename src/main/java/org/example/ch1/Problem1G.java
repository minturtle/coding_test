package org.example.ch1;

import java.io.*;
import java.util.*;


public class Problem1G {


    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine().trim());

        String[] patterns = br.readLine().trim().split("\\*");

        for(int i = 0; i < count; i++){
            String input = br.readLine().trim();

            boolean checkResult = check(input, patterns);

            bw.write(checkResult ? "DA" : "NE");
            if(i == count - 1){
                break;
            }
            bw.newLine();
        }

        bw.flush();

        br.close();
        bw.close();
    }

    static boolean check(String input, String[] patterns){
        if(
                input.startsWith(patterns[0]) &&
                input.endsWith(patterns[1]) &&
                input.length() >= (patterns[0].length() + patterns[1].length())
        ){
            return true;
        }

        return false;
    }

}
