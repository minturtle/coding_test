package org.example.ch4;


import java.io.*;
import java.util.*;


public class Problem4B {


    static int n;
    static int[] map; // 뒷면 true, 앞면 false, 비트마스킹 적용


    static int cnt = 0;
    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            n = Integer.parseInt(br.readLine());
            map = new int[n];

            for(int y = 0; y < n; y++){
                String s = br.readLine().trim();
                for(int x = 0; x < n; x++){
                    if(s.charAt(x) == 'H'){
                        continue;
                    }
                    map[y] |= (1 << (n - x - 1));
                    cnt++;
                }
            }

            execute();
            System.out.println(cnt);

        }

    }


    static void execute(){

        // 행에 대한 완전 탐색
        for(int choosedList = 0; choosedList < (1 << n); choosedList++){
            int[] copiedMap = Arrays.copyOf(map, map.length);

            for(int i = 0; i < n ; i++){
                if((choosedList & (1 << i)) == 0){
                    continue;
                }
                reverse(copiedMap, i, true);
            }

            cnt = Math.min(greedy(copiedMap), cnt);

        }

    }


    static void reverse(int[] map, int idx, boolean isRow){
        if(isRow){
            map[idx] = ~map[idx];
            return;
        }
        for(int i = 0; i < n; i++){
            map[i] ^= (1 << idx);
        }
    }


    // 특정 열에서 앞면보다 뒷면이 많은 경우 뒤집음.
    static int greedy(int[] map){
        int result = 0;

        for(int x = 0; x < n; x++){
            int backwardCnt = getColumnBackwardCnt(x, map);
            if(backwardCnt > n - backwardCnt){
                reverse(map, x, false);
                result += n - backwardCnt;
                continue;
            }
            result += backwardCnt;
        }

        return result;
    }

    static int getColumnBackwardCnt(int idx, int[] map){
        int result = 0;
        for(int y = 0; y < n; y++){
            if((map[y] & (1 << idx)) == 0){
                continue;
            }
            result++;
        }
        return result;
    }
}
