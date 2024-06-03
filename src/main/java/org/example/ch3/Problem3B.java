package org.example.ch3;


import java.io.*;
import java.util.*;

public class Problem3B {

    static int h;
    static int w;

    static int[][] map;
    static int max = -1;

    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new int[h][w];

        for(int y = 0; y < h; y++){
            char[] cArr = br.readLine().trim().toCharArray();
            for(int x = 0; x < w; x++){
                if(cArr[x] == 'W'){
                    map[y][x] = 0;
                }
                else{
                    map[y][x] = 1;
                }
            }
        }

        // 각 점에서 다른 점까지의 최단거리를 모두 구해 최단 거리중 가장 큰 값을 찾고, 이를 max와 비교
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                if(map[y][x] != 1){
                    continue;
                }

                int[][] bfsMap = bfs(x, y);
                max = Math.max(getMaxValue(bfsMap), max);
            }
        }

        System.out.println(max);
    }

    static int[][] bfs(int x, int y){
        int[][] bfsMap = new int[h][w];


        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{x, y});
        bfsMap[y][x] = 0;

        while(!queue.isEmpty()){
            Integer[] xy = queue.poll();

            for(int i = 0; i < 4; i++){
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if(nx < 0 || nx >= w || ny < 0 || ny >= h || map[ny][nx] == 0 || bfsMap[ny][nx] != 0){
                    continue;
                }


                bfsMap[ny][nx] = bfsMap[xy[1]][xy[0]] + 1;
                queue.add(new Integer[]{nx, ny});

            }
        }

        bfsMap[y][x] = 0;

        return bfsMap;
    }

    static int getMaxValue(int[][] bfsMap){
        int max = -1;

        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                max = Math.max(bfsMap[y][x], max);
            }
        }


        return max;
    }

}
