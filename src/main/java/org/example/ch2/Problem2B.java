package org.example.ch2;


import java.io.*;
import java.util.*;

public class Problem2B {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(br.readLine().trim());


        for(int i = 0; i < testCaseCount; i++){
            int[] mnk = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[][] map = new int[mnk[1]][mnk[0]];

            for(int j = 0 ; j < mnk[2]; j++){
                int[] xy =  Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                map[xy[1]][xy[0]] = 1;
            }

            int testResult = doTest(map, mnk[0], mnk[1]);

            bw.write(Integer.toString(testResult));
            bw.newLine();

        }

        bw.flush();
        br.close();
        bw.close();

    }

    public static int doTest(int[][] map, int xSize, int ySize){
        int result = 0;

        for(int i = 0; i < ySize; i++){
            for(int j = 0; j < xSize; j++){
                if(map[i][j] != 1){
                    continue;
                }
                dfs(map, j, i);
                result++;

            }
        }

        return result;
    }


    public static void dfs(int[][] map, int x, int y){
        map[y][x] = 2;

        int[][] dydxs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int[] dydx : dydxs){
            if(x + dydx[1] < 0 || x + dydx[1] >= map[0].length || y + dydx[0] < 0 || y + dydx[0] >= map.length){
                continue;
            }

            if(map[y + dydx[0]][x + dydx[1]] == 1){
                dfs(map, x + dydx[1], y + dydx[0]);
            }
        }

    }

}
