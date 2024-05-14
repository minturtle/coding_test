package org.example.ch1;


import java.util.*;
import java.io.*;

public class Problem1F {

    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        char[] input = br.readLine().toCharArray();


        for(int i =0; i < input.length; i++){
            int charType = checkCharType(input[i]);

            if(charType == 0){
                bw.write(input[i]);
                continue;
            }

            bw.write(convert(input[i], charType));

        }


        bw.flush();
        br.close();
        bw.close();
    }


    /**
     * c를 입력받아 소문자는 1, 대문자는 2, 나머지는 3을 반환
     *
     */
    static int checkCharType(char c){
        if(c >= 'a' && c <= 'z'){
            return 1;
        }
        else if(c >= 'A' && c <= 'Z'){
            return 2;
        }
        return 0;
    }

    static char convert(char c, int charType){
        switch(charType){
            case 1:
                return (char)((c + 13 - 'a') % 26 + 'a');
            case 2:
                return (char)((c + 13 - 'A') % 26 + 'A');
        }

        return c;
    }

}
