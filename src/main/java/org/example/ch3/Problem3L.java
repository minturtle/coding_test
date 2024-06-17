package org.example.ch3;

import java.io.*;
import java.util.*;

public class Problem3L {

    static final int[] dy = new int[]{-1, 1, 0, 0};
    static final int[] dx = new int[]{0, 0, -1, 1};
    static int h;
    static int w;
    static char[][] map;

    static boolean[] visited = new boolean[26];

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];

            for(int y = 0; y <h; y++){
                String s = br.readLine();
                for(int x = 0; x < w; x++){
                    map[y][x] = s.charAt(x);
                }
            }
        }

        System.out.println(execute(0, 0, 1));
    }


    static int execute(int x, int y, int cnt){
        int max = cnt;

        char c = map[y][x];
        visited[c - 'A'] = true;


        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= w || ny < 0 || ny >= h){
                continue;
            }
            char nc = map[ny][nx];
            if(visited[nc - 'A']){
                continue;
            }

            max = Math.max(max, execute(nx, ny, cnt + 1));
        }

        visited[c - 'A'] = false;
        return max;
    }
}
