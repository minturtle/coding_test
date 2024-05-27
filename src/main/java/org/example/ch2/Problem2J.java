package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2J {



    static boolean[][] map;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        map = new boolean[h][w];

        for(int i = 0; i < h; i++){
            char[] inputLine = br.readLine().trim().toCharArray();
            for(int j = 0; j < w; j++){
                map[i][j] = inputLine[j] == 'c';
            }
        }



        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                bw.write(Integer.toString(getNearestCloudDist(j, i)));
                bw.write(" ");
            }
            bw.newLine();
        }

        bw.flush();

        br.close();
        bw.close();


    }


    private static int getNearestCloudDist(int x, int y){
        for(int i = 0; i <= x; i++){
            if(map[y][x-i]){
                return i;
            }
        }

        return -1;
    }

}
