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
            for(; alphabetCnt[i] > 0;){
                if(isOdd(alphabetCnt[i])){
                    result[result.length / 2] = (char)(i + 'A');
                    alphabetCnt[i]--;
                }
                else{
                    result[resultIdx] = (char)(i + 'A');
                    result[result.length -1 - resultIdx] = (char)(i + 'A');
                    resultIdx++;
                    alphabetCnt[i]-=2;
                }

            }

        }

        bw.write(String.valueOf(result));
        bw.flush();

        bw.close();
        br.close();


    }


    static boolean pelMakeAvailable(int[] alphabetCnt, int size){
        /*
        * 펠린드롬을 만들 수 있는 경우
        * 1. 짝수
        *   1.1 홀수인 개체가 없어야함.
        * 2. 홀수
        *   2.1 홀수인 개체가 하나있어야함.
        *
        * */

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

