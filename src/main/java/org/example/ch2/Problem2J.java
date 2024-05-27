package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2J {



    static int[][] map;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        map = new int[h][w];

        for(int i = 0; i < h; i++){
            char[] inputLine = br.readLine().trim().toCharArray();
            for(int j = 0; j < w; j++){
                map[i][j] = inputLine[j] == 'c' ? 0 : -1;
            }
            int tmp = 0;
            for(int j = 0; j < w; j++){
                if(map[i][j] == 0){
                    tmp = 1;
                    continue;
                }
                if(map[i][j] == -1 && tmp != 0){
                    map[i][j] = tmp;
                }
                if(tmp != 0){
                    tmp++;
                }
            }

        }



        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                bw.write(Integer.toString(map[i][j]));
                bw.write(" ");
            }
            bw.newLine();
        }



        bw.flush();

        br.close();
        bw.close();


    }




}
