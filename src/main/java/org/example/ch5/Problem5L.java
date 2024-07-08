package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5L {



    static int n;
    static int[][] map;



    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

            n = Integer.parseInt(br.readLine());
            map = new int[n][n];

            for(int y = 0; y < n; y++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int x = 0; x < n; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(Integer.toString(execute(0, 0, 0)));
            bw.flush();
        }
    }


    static int execute(int depth, int choose, int startIdx){
        if(depth == n / 2){
            int startTeamValue = getTeamValue(choose, true);
            int linkTeamValue = getTeamValue(choose, false);

            return Math.abs(startTeamValue - linkTeamValue);
        }

        int min = Integer.MAX_VALUE;

        for(int i = startIdx; i < n; i++){
            int tmp = setBit(choose, i, true);
            min = Math.min(execute(depth + 1, tmp, i + 1), min);

        }

        return min;
    }


    static int getTeamValue(int choose, boolean isStartTeam){
        int result = 0;

        for(int i = 0; i < n; i++){
            if(getBit(choose, i) != isStartTeam){
                continue;
            }


            for(int j = 0; j < n; j++){
                if(getBit(choose, j) != isStartTeam){
                    continue;
                }

                result += map[i][j];
            }
        }


        return result;
    }

    static int setBit(int bits, int idx, boolean on){
        if(on){
            return bits | (1 << idx);
        }
        return bits & ~(1 << idx);
    }

    static boolean getBit(int bits, int idx){
        return (bits & (1 << idx)) != 0;

    }

}
