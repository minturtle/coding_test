package org.example.ch2;

import java.io.*;
import java.util.*;

public class Problem2H {
    static List<Character> moeums = List.of('a', 'e', 'i', 'o', 'u');
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        while(true){
            String inputStr = br.readLine().trim();
            if(inputStr.equals("end")){
                break;
            }

            if(test(inputStr)){
                bw.write(String.format("<%s> is acceptable.", inputStr));
            }
            else{
                bw.write(String.format("<%s> is not acceptable.", inputStr));
            }
            bw.newLine();
        }


        bw.flush();
        br.close();
        bw.close();

    }

    public static boolean test(String str){
        char[] cArr = str.toCharArray();
        return test1(cArr) && test2(cArr) && test3(cArr);
    }

    private static boolean test1(char[] cArr){
        for(char c : cArr){
            if(isMoeum(c)) {
               return true;
            }
        }


        return false;
    }

    private static boolean test2(char[] cArr){
        int moeumCount = 0; // 연속된 모음의 갯수
        int jaeumCount = 0; // 연속된 자음의 갯수

        for(int i = 0; i< cArr.length; i++){
            if(!isAlpha(cArr[i])){
                moeumCount = 0;
                jaeumCount = 0;
            }



            if(isMoeum(cArr[i])){
                if(moeumCount >= 2){
                    return false;
                }
                moeumCount++;
                jaeumCount = 0;
            }
            else{
                if(jaeumCount >= 2){
                    return false;
                }
                jaeumCount++;
                moeumCount = 0;

            }


        }


        return true;
    }

    private static boolean test3(char[] cArr){
        for(int i = 1; i < cArr.length; i++){
            if((cArr[i-1] == cArr[i]) && (cArr[i] != 'e') && (cArr[i] != 'o')){
                return false;
            }
        }
        return true;
    }

    private static boolean isMoeum(char c){
        return moeums.contains(c);
    }

    private static boolean isAlpha(char c){
        return c >= 'a' && c <= 'z';
    }

}
