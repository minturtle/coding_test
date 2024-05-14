package org.example.ch1;

import java.io.*;
import java.util.*;

public class Problem1H {


    static String[] dict;

    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dict = new String[nm[0] + 1];

        // 도감은 1번부터 사용
        for(int i = 1; i <= nm[0]; i++){
            dict[i] = br.readLine().trim();
        }

        for(int i = 0; i < nm[1]; i++){
            String question = br.readLine().trim();
            if(isNumber(question)){
                bw.write(dict[Integer.parseInt(question)]);
            }
            else{
                bw.write(getIndex(question));
            }
            bw.newLine();
        }


        bw.flush();

        br.close();
        bw.close();
    }


    /**
     * O(L) : Input Length L
     */

    static boolean isNumber(String input){
        try{
            int x = Integer.parseInt(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    /**
     *  O(N * L)
     *
     */

    static String getIndex(String input){
        for(int i = 1; i < dict.length; i++){
            if(!dict[i].equals(input)){
                continue;
            }
            return Integer.toString(i);
        }

        return "-1";
    }

}
