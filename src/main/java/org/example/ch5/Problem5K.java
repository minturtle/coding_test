package org.example.ch5;


import java.io.*;
import java.util.*;

public class Problem5K {


    static final int[] dy = new int[]{-1, 1, 0, 0};
    static final int[] dx = new int[]{0, 0, -1, 1};


    static int h;
    static int w;

    static int sec;

    static int[][] map;

    static int[] airCleanerPos = new int[2];


    public static void main(String[] args) throws IOException{
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int i = 0;

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            sec = Integer.parseInt(st.nextToken());

            map = new int[h][w];

            for(int y = 0; y < h; y++){
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x < w; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());

                    if(map[y][x] == -1){
                        airCleanerPos[i++] = y * w + x;
                    }
                }
            }

            simulate();

            bw.write(Integer.toString(getResult()));
            bw.flush();
        }
    }


    static void simulate(){

        for(int i = 0; i < sec; i++){
            moveDust();

            air();

        }

    }

    static void moveDust(){
        int[][] tmp = new int[h][w];


        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                if(map[y][x] == -1 || map[y][x] == -0){
                    continue;
                }

                int nDust = map[y][x] / 5;
                map[y][x] -= ( nDust * 4 );

                for(int i = 0; i < 4; i++){
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(nx < 0 || nx >= w || ny < 0 || ny >= h || map[ny][nx] == -1){
                        map[y][x] += nDust;
                        continue;
                    }
                    tmp[ny][nx] += nDust;
                }
            }
        }

        for(int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                map[y][x] += tmp[y][x];
            }
        }

    }



    // dest가 0이면 반시계 순환, 1이면 시계 순환
    static void air(int airPos, boolean dest, int startY, int endY) {
    }


    static int getResult(){
        int result = 0;
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++) {
                if(map[y][x] == -1){
                    continue;
                }

                result += map[y][x];
            }
        }

        return result;
    }

}
