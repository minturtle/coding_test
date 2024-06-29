package org.example.ch4;

import java.io.*;
import java.util.*;


public class Problem4J {


    static int h, w;

    static int[][] map;

    static int visited;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new int[h][w];

            for(int y = 0; y < h; y++){
                String s = br.readLine().trim();
                for(int x= 0; x< w; x++){
                    map[y][x] = s.charAt(x) - '0';
                }
            }

            bw.write(Integer.toString(execute()));
            bw.flush();
        }
    }


    static int execute(){
        int max = Integer.MIN_VALUE;

        for(int selectedMap = 0; selectedMap < (1 << (h * w)); selectedMap++){
            max = Math.max(getSum(selectedMap), max);
        }

        return max;
    }

    static int getSum(int map){
        visited = 0;

        int sum = 0;
        for(int y= 0; y< h; y++){
            for(int x = 0; x< w; x++){
                int pos = y * w + x;
                if(getBit(visited, pos)){
                    continue;
                }
                sum += dfs(map, x, y, getBit(map, pos), 0);
            }
        }

        return sum;
    }

    static int dfs(int selectedMap, int x, int y, boolean isColumn, int current){
        int pos = y * w + x;
        visited = setBit(visited, pos, true);

        int nx = x + (isColumn ? 0 : 1);
        int ny = y + (isColumn ? 1 : 0);
        int npos = ny * w + nx;


        if(nx < 0 || nx >= w || ny < 0 || ny >= h){
            return current * 10 + map[y][x];
        }
        if(getBit(selectedMap, npos) != getBit(selectedMap, pos)){
            return current * 10 + map[y][x];
        }

        return dfs(selectedMap, nx, ny, isColumn, current * 10 + map[y][x]);
    }


    static boolean getBit(int bits, int idx){
        return (bits & (1 << idx)) != 0;
    }

    static int setBit(int bits, int idx, boolean on){
        if(on){
            return (bits | (1 << idx));
        }
        return (bits & ~(1 << idx));

    }
}
