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
            air(true);
            air(false);
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



    static void air(boolean isUp) {
        int startX = 0;
        int startY;

        int[] dy;
        int[] dx;

        int dustSize;

        if(isUp){
            startY = airCleanerPos[0] / w;

            dy = new int[]{0, -1, 0, 1};
            dx = new int[]{1, 0, -1, 0};
            dustSize = w * 2 + startY * 2;

        }
        else{
            startY = airCleanerPos[1] / w;
            dy = new int[]{0, 1, 0, -1};
            dx = new int[]{1, 0, -1, 0};
            dustSize = w * 2 + (h - startY + 1) * 2;
        }

        int[] moveDusts = getMoveDusts(startY, startX, dx, dy, dustSize);

        moveDust(startX, dx, startY, dy, moveDusts);

    }

    private static int[] getMoveDusts(int startY, int startX, int[] dx, int[] dy, int resultSize) {
        int [] tmp = new int[resultSize];


        int x = startX + dx[0];
        int y = startY + dy[0];

        int pt = 1;
        int directPt = 0;

        while(x != startX || y != startY){
            tmp[pt++] = map[y][x];

            int nx = x + dx[directPt];
            int ny = y + dy[directPt];

            if(nx < 0 || nx >= w || ny < 0 || ny >= h){
                directPt++;
                nx = x + dx[directPt];
                ny = y + dy[directPt];
            }

            x = nx;
            y = ny;
        }
        return tmp;
    }


    private static void moveDust(int startX, int[] dx, int startY, int[] dy, int[] moveDusts) {
        int directPt;
        int x;
        int y;
        int pt;

        x = startX + dx[0];
        y = startY + dy[0];
        pt = 0;
        directPt = 0;

        while(x != startX || y != startY){
            map[y][x] = moveDusts[pt++];

            int nx = x + dx[directPt];
            int ny = y + dy[directPt];

            if(nx < 0 || nx >= w || ny < 0 || ny >= h){
                directPt++;
                nx = x + dx[directPt];
                ny = y + dy[directPt];
            }

            x = nx;
            y = ny;
        }
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
