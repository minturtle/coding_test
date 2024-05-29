package org.example.ch2;

import java.io.*;
import java.util.*;

public class Problem2Q {


    static int[][] map;
    static int h;
    static int w;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        int prev = 0;


        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    prev++;
                }
            }
        }

        int time = 0;


        while(true){

            time++;
            melt();
            int currentCheeseCnt = getCheeseCnt();

            if(currentCheeseCnt == 0){
                break;
            }

            prev = currentCheeseCnt;
        }

        System.out.println(time);
        System.out.println(prev);

    }

    static void melt(){
        boolean[][] visited = new boolean[h][w];

        dfs(0, 0, visited);

        meltMarkedCheese();

    }

    static int getCheeseCnt(){
        int cnt = 0;
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                if(map[y][x] == 1){
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static void dfs(int x, int y, boolean[][] visited){
        visited[y][x] = true;


        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= w || ny < 0 || ny >= h || visited[ny][nx]){
                continue;
            }

            if(map[ny][nx] == 1){
                map[ny][nx] = 2;
            }
            else if(map[ny][nx] == 0){
                dfs(nx, ny, visited);
            }
        }


    }

    static void meltMarkedCheese(){
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                if(map[y][x] == 2){
                    map[y][x] = 0;
                }
            }
        }

    }

}