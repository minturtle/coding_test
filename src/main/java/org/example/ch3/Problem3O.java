package org.example.ch3;

import java.io.*;
import java.util.*;

public class Problem3O {

    static int n, m, h;

    static int answer = 4;

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


            execute(0, 0, 0);
            System.out.println(answer == 4 ? -1 : answer);
        }
    }

    static void execute(int startX, int startY, int cnt){
        if(cnt > 3 || answer <= cnt){
            return;
        }

        if(simulate()){
            answer = cnt;
        }

        for(int y = startY; y < h; y++){
            for(int x = (y == startY? startX : 0); x < n-1; x++){
                if(map[y][x]){
                    continue;
                }

                map[y][x] = true;
                execute(x + 2, y, cnt + 1);
                map[y][x] = false;
            }
        }
    }



    // 사다리 타기를 해서 모든 i번째 세로줄이 i로 가면 true( 0 <= i < n)
    static boolean simulate(){
        for(int i = 0; i < n; i++){
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
