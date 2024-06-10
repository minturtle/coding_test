package org.example.ch3;

import java.io.*;
import java.util.*;

public class Problem3E {

    static int[][] routes = new int[][]{
            {9, 3, 1},
            {9, 1, 3},
            {3, 9 ,1},
            {3, 1, 9},
            {1, 3, 9},
            {1, 9, 3}
    };
    static int n;
    static int[] scvs;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        scvs = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            scvs[i] = Integer.parseInt(st.nextToken());
        }


        int min = simulate();

        System.out.println(min);

    }



    static int simulate(){
        int[][][] visited = new int[scvs[0] + 1][scvs[1] + 1][scvs[2] + 1];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(scvs);

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            for(int[] route : routes){
                int[] attackedScvs = attack(current, route);

                if(visited[attackedScvs[0]][attackedScvs[1]][attackedScvs[2]] != 0){
                    continue;
                }

                if(isAllDead(attackedScvs)){
                    return visited[current[0]][current[1]][current[2]] + 1;
                }

                visited[attackedScvs[0]][attackedScvs[1]][attackedScvs[2]] =
                        visited[current[0]][current[1]][current[2]] + 1;
                queue.add(attackedScvs);
            }

        }

        return -1;

    }

    static int[] attack(int[] scvRemains, int[] route){
        int[] result = scvRemains.clone();

        for(int i = 0; i < n; i++){
            result[i] = Math.max(0, result[i]-route[i]);
        }

        return result;
    }


    static boolean isAllDead(int[] scvRemains){
        for(int i = 0; i <n; i++){
            if(scvRemains[i] > 0){
                return false;
            }
        }

        return true;
    }



}
