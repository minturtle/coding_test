package org.example.ch4;


import java.io.*;
import java.util.*;


public class Practice4E {

    static int n;
    static int l;
    static int[][] map1;
    static int[][] map2;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            map1 = new int[n][n];
            map2 = new int[n][n];

            for(int y = 0; y < n; y++){
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x < n; x++){
                    map1[y][x] = st.nextToken().charAt(0) - '0';
                    map2[x][y] = map1[y][x];
                }
            }

            System.out.println(execute(map1)+execute(map2));
        }

    }

    static int execute(int[][] map){
        int result = 0;

        for(int y = 0; y < n; y++){
            if(!isRoad(map, y)){
                continue;
            }
            result++;
        }

        return result;

    }



    static boolean isRoad(int[][] map , int idx){

        int cnt = 1;
        int x;
        for(x = 0; x < n-1; x++){
            if(map[idx][x+1] == map[idx][x]){
                cnt++;
                continue;
            }
            // 오르막길일시
            if(map[idx][x] + 1 ==  map[idx][x+1] && cnt >= l){
                cnt = 1;
                continue;
            }
            // 내리막 길일시
            if(map[idx][x]== map[idx][x+1] + 1 && cnt >= 0){
                cnt = -l + 1;
                continue;
            }

            break;
        }

        return x == n - 1 && cnt >= 0;

    }


}
