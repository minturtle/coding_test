package org.example.ch3;

import java.io.*;
import java.util.*;

public class Problem3P {

    static int n;
    static int[][] priceMap;

    static boolean[][] visited;

    static int minPrice = Integer.MAX_VALUE;

    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException{

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            n = Integer.parseInt(br.readLine());
            priceMap = new int[n][n];
            visited = new boolean[n][n];

            for(int y = 0; y < n; y++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int x = 0; x < n; x++){
                    priceMap[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            execute(1, 1, 0, 0);
            System.out.println(minPrice);
        }
    }

    static void execute(int startX, int startY, int cnt, int price){
        if(price >= minPrice){
            return;
        }
        if(cnt == 3){
            minPrice = Math.min(minPrice, price);
            return;
        }
        if(startY >= n-1){
            return;
        }

        for(int y = startY; y < n - 1; y++){
            for(int x = (y == startY? startX : 1); x < n -1; x++){
                if(isDie(x, y)){
                    continue;
                }

                bloomOrClose(x, y, true);
                execute(x + 2, y, cnt + 1, price + calculatePrice(x, y));
                bloomOrClose(x, y, false);

            }
        }
    }


    static boolean isDie(int x, int y){
        if(visited[y][x]){
            return true;
        }

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(visited[ny][nx]){
                return true;
            }

        }

        return false;
    }

    static void bloomOrClose(int x, int y, boolean bloom){
        visited[y][x] = bloom;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            visited[ny][nx] = bloom;
        }
    }
    static int calculatePrice(int x, int y){
        int result = 0;
        result += priceMap[y][x];

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            result += priceMap[ny][nx];
        }

        return result;
    }
}
