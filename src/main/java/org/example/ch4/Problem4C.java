package org.example.ch4;

import java.io.*;
import java.util.*;


public class Problem4C {

    static int n;
    static int[] peopleCount;
    static int[][] adjList;


    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            peopleCount = new int[n];
            adjList = new int[n][];
            for(int i = 0; i < n; i++){
                peopleCount[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                adjList[i] = new int[Integer.parseInt(st.nextToken())];
                for(int j = 0; j < adjList[i].length; j++){
                    adjList[i][j] = Integer.parseInt(st.nextToken()) - 1;
                }

            }

            execute();

            System.out.println(min == Integer.MAX_VALUE ? "-1" : min);
        }
    }


    static void execute(){

        // 1~ 2^n -2 (모두 안뽑는 경우와 모두 뽑는 경우 제외)
        for(int groupA = 1; groupA < (1 << n) - 1; groupA++){
            int groupB = ~groupA;


            if(!isOk(groupA, groupB)){
                continue;
            }

            int v = calculatePeopleGap(groupA);
            min = Math.min(min, v);
        }
    }


    /**
     * 그룹으로 나눌수 있는지 여부 체크
     * */
    static boolean isOk(int groupA, int groupB){

        return bfs(groupA) && bfs(groupB);
    }

    static int calculatePeopleGap(int groupA){
        int valueA = 0, valueB = 0;
        for(int i = 0; i < n; i++){
            // i가 groupA라면
            if(isBitOn(groupA, i)){
                valueA += peopleCount[i];
                continue;
            }
            valueB += peopleCount[i];
        }

        return Math.abs(valueA - valueB);
    }

    static boolean bfs(int group){

        Queue<Integer> queue = new LinkedList<>();

        int leastBit = Integer.numberOfTrailingZeros(group);

        int visited = turnOnBit(0, leastBit);
        queue.add(leastBit);

        while(!queue.isEmpty()){
            Integer bit = queue.poll();

            for(int adj : adjList[bit]){
                if(isBitOn(visited, adj) || !isBitOn(group, adj)){
                    continue;
                }
                visited = turnOnBit(visited, adj);
                queue.add(adj);
            }
        }

        return isAllGroupConnected(group, visited);
    }

    static boolean isBitOn(int bits, int idx){
        return (bits & (1 << idx)) != 0;
    }

    static int turnOnBit(int bits, int idx){
        bits |= (1 << idx);
        return bits;
    }



    static boolean isAllGroupConnected(int group, int visited){

        for(int i = 0; i < n; i++){
            if(isBitOn(group, i) && !isBitOn(visited, i)){
                return false;
            }
        }
        return true;
    }
}
