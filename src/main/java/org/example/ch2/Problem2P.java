package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2P {

    static int[][] map;
    static int h;
    static int w;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new int[h][];

        for(int i = 0; i < h; i++){
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int maxSpaceSize = getMaxSpaceSize();


        System.out.println(maxSpaceSize);

        br.close();

    }

    static int getMaxSpaceSize(){
        return combi(0);
    }


    // 3-depth +1 개의 벽을 선택하는데, 선택하는 경우의 수 중 가장 safeArea가 넓은 경우의 수의 넓이를 반환
    static int combi(int depth){
        if(depth == 3){
            int[][] simulateMap = simulateVirus();
            return getSafeAreaSize(simulateMap);
        }

        int max = 0;

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(map[i][j] != 0){
                    continue;
                }

                map[i][j] = 1;
                int safeAreaSize = combi(depth + 1);
                max = Math.max(max, safeAreaSize);
                map[i][j] = 0;
            }
        }

        return max;
    }

    static int[][] simulateVirus(){
        int[][] cloneMap = new int[h][w];
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                cloneMap[i][j] = map[i][j];
            }
        }


        boolean[][] visited = new boolean[h][w];

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(cloneMap[i][j] == 2 && !visited[i][j]){
                    dfs(cloneMap, visited, j, i);
                }
            }
        }

        return cloneMap;
    }

    static void dfs(int[][] map, boolean[][] visited, int x, int y){
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        map[y][x] = 2;
        for(int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= h || nx < 0 || nx >= w || visited[ny][nx]){
                continue;
            }
            if(map[ny][nx] != 0){
                continue;
            }

            visited[ny][nx] = true;
            dfs(map, visited, nx, ny);
        }
    }

    static int getSafeAreaSize(int[][] map){
        int cnt = 0;
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(map[i][j] == 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }

}
