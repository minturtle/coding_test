package org.example.ch2;

import java.io.*;
import java.util.*;

public class Problem2C {


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(br.readLine().trim());

        int[][] map = new int[N][];

        for(int i = 0; i < N; i++){
            map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        }

        int result = getMaxSafeArea(map);

        bw.write(Integer.toString(result));

        bw.flush();

        br.close();
        bw.close();


    }

    private static int getMaxSafeArea(int[][] map) {
        int max = getSafeAreaCount(map); //비가 안오는 경우부터 시작

        for(int i = 0; i < 100; i++){
            upWaterHeightInMap(map);
            int safeAreaCount = getSafeAreaCount(map);

            max = max > safeAreaCount ? max : safeAreaCount;
        }

        return max;
    }


    private static int getSafeAreaCount(int[][] map){
        int n = map.length;
        int[][] visited = new int[n][n];
        int count = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] > 0 && visited[i][j] == 0){
                    dfs(map, visited, j, i);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(int[][] map, int[][] visited, int x, int y){
        visited[y][x] = 1;

        int[] dy = new int[]{ -1, 1, 0 ,0};
        int[] dx = new int[]{ 0, 0, -1, 1};

        for(int i = 0; i < 4; i++){
            int adjXPos = x + dx[i];
            int adjYPos = y + dy[i];

            if(adjXPos < 0 || adjXPos >= map.length || adjYPos < 0 || adjYPos >= map.length){
                continue;
            }

            if(map[adjYPos][adjXPos] > 0 && visited[adjYPos][adjXPos] == 0){
                dfs(map, visited, adjXPos, adjYPos);
            }


        }


    }


    private static void upWaterHeightInMap(int[][] map){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map.length; j++){
                map[i][j]--;
            }
        }

    }
}
