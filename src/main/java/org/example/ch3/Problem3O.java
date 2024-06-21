package org.example.ch3;

import java.io.*;
import java.util.*;

public class Problem3O {

    static int n, m, h;

    static boolean map[][];

    public static void main(String[] args)throws IOException{

        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new boolean[h][n-1];

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());

                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;

                map[y][x] = true;
            }


            System.out.println(execute());
        }
    }

    static int execute(){

        for(int i = 0; i < 4; i++){
            if(!combination(i,0, 0)){
                continue;
            }

            return i;
        }

        return -1;
    }

    // cnt개의 좌표를 뽑은 후, 시물레이션을 통해 모든 세로줄 i가 i번으로 가는지 확인
    static boolean combination(int c, int cnt, int start){
        if(c == cnt){
            return simulate();
        }

        for(int i = start; i < (n-1) * h; i++){
            int x = i % (n-1);
            int y = i / (n-1);
            if(map[y][x]){
                continue;
            }

            map[y][x] = true;
            if(combination(c, cnt + 1, i+1)){
                return true;
            };
            map[y][x] = false;
            if(combination(c, cnt, i+1)){
                return true;
            };
        }

        return false;
    }



    // 사다리 타기를 해서 모든 i번째 세로줄이 i로 가면 true
    static boolean simulate(){
        for(int i = 0; i < n-1; i++){
            int flag = i;

            for(int j = 0; j < h; j++){
                if(flag < n-1 && map[j][flag]){
                    flag++;
                }
                else if(flag > 0 && map[j][flag-1]){
                    flag--;
                }
            }

            if(flag != i){
                return false;
            }

        }

        return true;
    }
}
