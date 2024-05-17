package org.example.ch1;


import java.io.*;
import java.util.*;

public class Problem1M {


    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());

        int result = 0;

        for(int i = 0; i < n; i++){
            String input = br.readLine().trim();
            if(isGoodWord(input)){
                result++;
            }
        }



        bw.write(Integer.toString(result));
        bw.flush();

        br.close();
        bw.close();

    }



    public static boolean isGoodWord(String str){
        /*
        * Good Word가 아닌 경우
        * 1. A또는 B의 갯수가 홀수인 경우
        * 2. 아치안에 문자열이 홀수개 인경우
        * */
        int[] archSelected = new int[str.length()];
        char[] cArr = str.toCharArray();

        for(int idx = 0; idx < cArr.length; idx++){
            if(archSelected[idx] == 1){
                continue;
            }

            int pairIdx = findPair(idx, cArr, archSelected);
            if(pairIdx == -1){
                return false; //아치 생성이 불가능하면 갯수가 홀수
            }
            if((pairIdx - idx -1) % 2 == 1){
                return false; // 생성된 아치 사이에 문자의 갯수가 홀수면 안됨.
            }

        }

        return true;
    }

    public static int findPair(int idx, char[] cArr, int[] archSelected){
        char c = cArr[idx];
        for(int i = cArr.length-1; i > idx; i--){
            if(cArr[i] == c && archSelected[i] == 0){
                archSelected[idx] = 1;
                archSelected[i] = 1;
                return i;
            }
        }

        return -1;
    }

}



