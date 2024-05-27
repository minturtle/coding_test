package org.example.ch2;


import java.io.*;
import java.util.*;

public class Problem2K {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < n ; i++){
            int input = Integer.parseInt(br.readLine().trim());
            int result = getRightZeroCount(input);

            bw.write(Integer.toString(result));
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }


    static int getRightZeroCount(int number){
        /*
        * twoFiveCount[0] 은 팩토리얼에서 2의 갯수
        * twoFiveCount[1] 은 팩토리얼에서 5의 갯수
        * */
        int[] twoFiveCount = new int[2];

        for(int i = 1; i <= number; i++){
            twoFiveCount[0] += getTwoCount(i);
            twoFiveCount[1] += getFiveCount(i);
        }

        int min = Math.min(twoFiveCount[0], twoFiveCount[1]);

        return min;

    }


    // num에서 2로 나누어지는 횟수가 몇 회인지 구하는 함수
    static int getTwoCount(int num){
        int cnt = 0;
        while(true){
            if(num % 2 != 0){
                break;
            }
            num /= 2;
            cnt++;
        }

        return cnt;
    }

    // num에서 5로 나누어지는 횟수가 몇 회인지 구하는 함수
    static int getFiveCount(int num){
        int cnt = 0;
        while(true){
            if(num % 5 != 0){
                break;
            }
            num /= 5;
            cnt++;
        }

        return cnt;
    }

}
