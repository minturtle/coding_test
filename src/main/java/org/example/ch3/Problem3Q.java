package org.example.ch3;


import java.io.*;
import java.util.*;

public class Problem3Q {

    static int r;
    static int c;
    static int k;

    static boolean[][] map;
    static boolean[][] visited;

    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new boolean[r][c];
            visited = new boolean[r][c];

            // y를 역순으로 받아서 상하반전 -> 출발지를 (0,0)으로, 도착지를 (c-1, r-1)로 변경
            for(int y = r - 1; y >= 0; y--){
                String s = br.readLine().trim();
                for(int x = 0; x < c; x++){
                    map[y][x] = s.charAt(x) == '.';
                }
            }

            visited[0][0] = true;
            bw.write(Integer.toString(dfs(0, 0, 1)));
            bw.flush();
        }

    }

    static int dfs(int x, int y, int cnt){
        if(cnt == k){
            return x == c - 1 && y == r - 1 ? 1 : 0;
        }

        int result = 0;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= c || ny < 0 || ny >= r || visited[ny][nx]){
                continue;
            }
            if(!map[ny][nx]){
                continue;
            }

            visited[ny][nx] = true;
            result += dfs(nx, ny, cnt+1);
            visited[ny][nx] = false;
        }

        return result;
    }

}
