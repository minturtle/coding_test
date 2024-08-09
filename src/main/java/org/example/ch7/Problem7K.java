package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7K {

    private static int[][] map;
    private static int h;
    private static int w;

    private static int[][][][] dp;

    private static final int[] dx = new int[]{1, 0};
    private static final int[] dy = new int[]{0, 1};
    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int c = Integer.parseInt(st.nextToken());

            map = new int[h][w];
            dp = new int[c + 1][w][h][c + 1];

            for(int i = 0; i <= c; i++){
                for(int j = 0; j < w; j++){
                    for(int k = 0; k < h; k++){
                        Arrays.fill(dp[i][j][k], -1);
                    }
                }
            }

            for(int i = 1; i <= c; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) -1 ;
                int y = Integer.parseInt(st.nextToken()) -1 ;

                map[y][x] = i;
            }

            StringJoiner sj = new StringJoiner(" ");
            for(int i = 0; i <= c; i++){
                if(map[0][0] != 0){
                    sj.add(Integer.toString(dfs(i-1, 0, 0, map[0][0])));
                    continue;
                }
                sj.add(Integer.toString(dfs(i, 0, 0, 0)));
            }

            bw.write(sj.toString());
            bw.flush();
        }
    }

    static int dfs(int cnt, int x, int y, int prev){
        if(cnt < 0){
            return 0;
        }

        if(dp[cnt][x][y][prev] != -1){
            return dp[cnt][x][y][prev];
        }

        if(x == w-1 && y == h-1){
            dp[cnt][x][y][prev] = cnt == 0 ? 1 : 0;
            return dp[cnt][x][y][prev];
        }

        dp[cnt][x][y][prev] = 0;

        for(int i = 0; i < 2; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!checkMoveable(cnt, nx, ny, prev)){
                continue;
            }
            if(map[ny][nx] != 0){
                dp[cnt][x][y][prev] += (dfs(cnt - 1, nx, ny, map[ny][nx]) % 1_000_007);
                continue;
            }
            dp[cnt][x][y][prev] += (dfs(cnt, nx, ny, prev) % 1_000_007);
        }

        dp[cnt][x][y][prev] = dp[cnt][x][y][prev] % 1_000_007;
        return dp[cnt][x][y][prev];
    }


    static boolean checkMoveable(int cnt, int nx, int ny, int prev){
        if(nx < 0 || nx >= w || ny < 0 || ny >= h){
            return false;
        }
        if(cnt == 0 && map[ny][nx] != 0){
            return false;
        }
        if(map[ny][nx] != 0 && prev >= map[ny][nx]){
            return false;
        }

        return true;
    }
}
