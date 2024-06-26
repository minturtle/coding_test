package org.example.ch4;

import java.io.*;
import java.util.*;

public class Problem4D {


    static final int[] dy = new int[]{-1, 1, 0, 0};
    static final int[] dx = new int[]{0, 0, -1, 1};

    static int h;
    static int w;

    static char[][] map;



    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){

            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];

            for(int y = 0; y < h; y++){
                String s = br.readLine().trim();
                for(int x = 0; x < w; x++){
                    map[y][x] = s.charAt(x);
                }
            }
            System.out.println(execute(0,0, setVisited(0, map[0][0] - 'A', true)));
        }
    }


    static int execute(int x, int y, int visited){
        int result = 1;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= w || ny < 0 || ny >= h || isVisited(visited,map[ny][nx] - 'A')){
                continue;
            }

            result = Math.max(
                    execute(nx, ny, setVisited(visited, map[ny][nx] - 'A', true)) + 1,
                    result
            );
        }

        return result;
    }


    static int setVisited(int visited, int idx, boolean on){
        if(on){
            return visited |= (1 << idx);
        }
        return visited &= ~(1 << idx);
    }

    static boolean isVisited(int visited, int idx){
        return (visited & (1 << idx)) != 0;
    }
}
