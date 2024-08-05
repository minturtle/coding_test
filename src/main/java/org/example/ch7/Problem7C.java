package org.example.ch7;

import java.io.*;
import java.util.*;

public class Problem7C {


    private static final int HOLE = 10;

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    private static int n;
    private static int m;

    private static int[][] map, dp;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            dp = new int[n][m];
            visited = new boolean[n][m];

            for(int i = 0; i < n; i++){
                String s = br.readLine().trim();
                for(int j = 0; j < m; j++){
                    if(s.charAt(j) == 'H'){
                        map[i][j] = HOLE;
                        continue;
                    }
                    map[i][j] = s.charAt(j) - '0';
                }
                Arrays.fill(dp[i], -1);
            }

            visited[0][0] = true;
            bw.write(Integer.toString(dfs(0, 0)));
            bw.flush();
        }
    }

    static int dfs(int x, int y){
        if(dp[y][x] != -1){
            return dp[y][x];
        }

        int value = map[y][x];

        dp[y][x] = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + (dx[i] * value);
            int ny = y + (dy[i] * value);

            if(nx < 0 || nx >= m || ny < 0 || ny >= n || map[ny][nx] == HOLE){
                continue;
            }
            if(visited[ny][nx]){
                return -1;
            }

            visited[ny][nx] = true;
            int tmp = dfs(nx, ny);
            if(tmp == -1){
                return tmp;
            }

            dp[y][x] = Math.max(tmp, dp[y][x]);
            visited[ny][nx] = false;

        }

        return ++dp[y][x];
    }

}
