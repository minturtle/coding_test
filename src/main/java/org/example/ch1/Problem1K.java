package org.example.ch1;

import java.util.*;
import java.io.*;


public class Problem1K {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine().trim();

        int[] alphabetCnt = new int[26];

        for(int i = 0; i < input.length(); i++){
            alphabetCnt[input.charAt(i) - 'A']++;
        }

        if(!pelMakeAvailable(alphabetCnt, input.length())){
            bw.write("I'm Sorry Hansoo");
            bw.flush();

            bw.close();
            br.close();
            return;
        }

        char[] result = new char[input.length()];
        int resultIdx = 0;
        for(int i = 0; i < 26; i++){
            int count = alphabetCnt[i];

            if(isOdd(count)){
                result[result.length / 2 + 1] = (char)(i + 'A');
                continue;
            }

            int cntIdx = count / 2;
            for(int j = 0; j < cntIdx; j++){
                result[resultIdx] = (char)(i + 'A');
                result[result.length -1 - resultIdx] = (char)(i + 'A');
                resultIdx++;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();

        bw.close();
        br.close();


    }


    static boolean pelMakeAvailable(int[] alphabetCnt, int size){
        int oddCnt = 0;

        for(int i = 0; i <26 && oddCnt < 2; i++){
            if(isOdd(alphabetCnt[i])){
                oddCnt++;
            }
        }

        if(oddCnt == 1 && isOdd(size)) return true;
        else if(oddCnt == 0 && !isOdd(size)) return true;
        return false;
    }

    private static boolean isOdd(int alphabetCnt) {
        return alphabetCnt % 2 == 1;
    }


}
