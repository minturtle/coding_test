package org.example.ch3;


import java.io.*;
import java.util.*;

public class Problem3D {

    private static final char JIHOON = 'J';
    private static final char WALL = '#';
    private static final char FIRE = 'F';
    private static final char ROAD = '.';


    static int h;
    static int w;

    static char[][] map;
    static int[][] fireMap;

    static int[] dy = new int[]{-1 , 1, 0 ,0};
    static int[] dx = new int[]{0, 0, -1, 1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        fireMap = initMap();

        int[] jihoonPos = new int[2];

        for(int y = 0; y < h; y++){
            String str = br.readLine().trim();

            for(int x = 0; x < w; x++){
               map[y][x] = str.charAt(x);

               if(map[y][x] == FIRE){
                   fireMap[y][x] = 0;
               }
               if(map[y][x] == JIHOON){
                   jihoonPos = new int[]{x, y};
               }

           }
        }

        setUpFireMap();

        int fastestTime = getFastestTime(jihoonPos[0], jihoonPos[1]);


        if(fastestTime == -1){
            System.out.println("IMPOSSIBLE");
        }
        else{
            System.out.println(fastestTime + 1);
        }


        br.close();
    }


    static int[][] initMap(){
        int[][] map = new int[h][w];

        for(int i = 0; i <h; i++){
            for(int j =0; j < w; j++){
                map[i][j] = Integer.MAX_VALUE;
            }
        }

        return map;
    }

    static void setUpFireMap() {
        Queue<Integer[]> queue = new LinkedList<>();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (fireMap[y][x] == 0) {
                    queue.add(new Integer[]{x, y});
                }
            }
        }
        bfsFire(queue);
    }

    static void bfsFire(Queue<Integer[]> queue) {
        while (!queue.isEmpty()) {
            Integer[] xy = queue.poll();
            int value = fireMap[xy[1]][xy[0]];

            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx < 0 || nx >= w || ny < 0 || ny >= h) {
                    continue;
                }
                if (map[ny][nx] == WALL) {
                    continue;
                }

                if (fireMap[ny][nx] <= value + 1) {
                    continue;
                }
                fireMap[ny][nx] = value + 1;
                queue.add(new Integer[]{nx, ny});
            }
        }
    }


    static int getFastestTime(int jihoonX, int jihoonY){
        int[][] jihoonMap = initMap();

        Queue<Integer[]> queue = new LinkedList<>();

        queue.add(new Integer[]{jihoonX, jihoonY});
        jihoonMap[jihoonY][jihoonX] = 0;

        while(!queue.isEmpty()){
            Integer[] xy = queue.poll();
            int value = jihoonMap[xy[1]][xy[0]];

            if(isEnd(xy[0], xy[1])){
                return value;
            }

            for(int i = 0; i < 4; i++){
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if(nx < 0 || nx >= w || ny < 0 || ny >= h || jihoonMap[ny][nx] != Integer.MAX_VALUE){
                    continue;
                }
                if(map[ny][nx] != ROAD){
                    continue;
                }
                if(fireMap[ny][nx] <= value + 1) {
                    continue;
                }

                jihoonMap[ny][nx] = value + 1;
                queue.add(new Integer[]{nx, ny});
            }

        }


        return -1;
    }

    static boolean isEnd(int x, int y){
        if(map[y][x] == FIRE || map[y][x] == WALL){
            return false;
        }

        return x == 0 || x == w-1 || y == 0 || y == h-1;
    }
}
