package org.example.ch4;


import java.io.*;
import java.util.*;


public class Problem4H {

    static int h, w;

    static int[][] map;

    static int result1;
    static int result2 = Integer.MIN_VALUE;

    static int result3 = Integer.MIN_VALUE;

    static int[] dx = new int[]{-1, 0, 1, 0}; //서 북 동 남 순
    static int[] dy = new int[]{0, -1, 0 , 1};


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[h][w];

            for(int y = 0; y < h; y++){
                st = new StringTokenizer(br.readLine().trim());
                for(int x = 0; x < w; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }


            getResult1And2();
            getResult3();
            bw.write(Integer.toString(result1));
            bw.newLine();

            bw.write(Integer.toString(result2));
            bw.newLine();

            bw.write(Integer.toString(result3));

            bw.flush();
        }
    }




    static void getResult1And2(){
        SimulateResult result = simulate();

        result1 = result.cnt;
        result2 = result.maxRoomSize;
    }


    static void getResult3(){
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++) {
                for(int i = 0; i < 4; i++){
                    if(!isWall(map[y][x], i)){
                        continue;
                    }
                    executeWall(x, y, i,false);
                    result3 = Math.max(simulate().maxRoomSize, result3);
                    executeWall(x, y, i, true);
                }
            }
        }

    }

    static SimulateResult simulate(){
        SimulateResult result = new SimulateResult();

        boolean[][] visited = initVisited();
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                if(visited[y][x]){
                    continue;
                }
                result.cnt++;
                result.maxRoomSize = Math.max(dfs(x, y, visited), result.maxRoomSize);
            }
        }
        return result;
    }


    private static boolean[][] initVisited(){
        return new boolean[h][w];
    }

    private static int dfs(int x, int y, boolean[][] visited){
        visited[y][x] = true;
        int size = 1;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isWall(map[y][x], i)){
                continue;
            }
            if(nx < 0 || nx >= w || ny < 0 || ny >= h || visited[ny][nx]){
                continue;
            }

            size += dfs(nx, ny, visited);
        }

        return size;
    }


    private static boolean isWall(int value, int direction){
        return (value & ( 1 << direction)) != 0;
    }

    private static void executeWall(int x, int y, int idx, boolean isCreate){
        if(isCreate){
            map[y][x] |= (1 << idx);
            return;
        }
        map[y][x] &= ~(1 << idx);
    }

    static class SimulateResult{
        public int cnt = 0;
        public int maxRoomSize = 0;



    }
}
