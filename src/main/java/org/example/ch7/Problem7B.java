package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7B {

    private static enum Direction{
        RIGHT(0, 1, 0), DOWN(1, 0, 1), RIGHT_DOWN(2,1,1);

        Direction(int number, int dx, int dy) {
            this.number = number;
            this.dx = dx;
            this.dy = dy;
        }

        int number;
        int dx;
        int dy;
    }


    static int[][] map;
    static int n;

    static int[][][] dp;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

           n = Integer.parseInt(br.readLine());
           map = new int[n][n];
           initDp();

           for(int y = 0; y < n; y++){
               StringTokenizer st = new StringTokenizer(br.readLine());
               for(int x = 0; x < n; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
               }
           }

            bw.write(Integer.toString(dfs(0, 0, Direction.RIGHT)));
            bw.flush();
        }
    }

    private static int dfs(int x, int y, Direction direction){
        if(dp[x][y][direction.number] != -1){
            return dp[x][y][direction.number];
        }

        // nx, ny는 다음 xy좌표임과 동시에 파이프의 도착지점
        int nx = x + direction.dx;
        int ny = y + direction.dy;

        if(nx == n-1 && ny == n-1){
            return 1;
        }

        dp[x][y][direction.number] = 0;
        for(Direction nextDirection : getAvaiableNextDirection(direction)){
            if(!check(nx, ny, nextDirection)){
                continue;
            }

            dp[x][y][direction.number] += dfs(nx, ny, nextDirection);
        }

        return dp[x][y][direction.number];
    }


    static boolean check(int x, int y, Direction direction){
        switch (direction){
            case RIGHT:
                return isBlank(x + 1, y);
            case RIGHT_DOWN:
                return isBlank(x+1, y) && isBlank(x+1, y + 1) && isBlank(x, y + 1);
            default:
                return isBlank(x, y+1);
        }
    }


    static boolean isBlank(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n){
            return false;
        }
        return map[y][x] == 0;
    }

    static Direction[] getAvaiableNextDirection(Direction current){
        switch (current){
            case RIGHT:
                return new Direction[]{Direction.RIGHT_DOWN, Direction.RIGHT};
            case RIGHT_DOWN:
                return new Direction[]{Direction.RIGHT, Direction.RIGHT_DOWN, Direction.DOWN};

        }

        return new Direction[]{Direction.RIGHT_DOWN, Direction.DOWN};
    }


    static void initDp(){
        dp = new int[n][n][3];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
    }
}
