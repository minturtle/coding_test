package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2A {


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] xy = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] map = new int[xy[0]][];
        
        for(int i = 0; i < xy[0]; i++){
            map[i] = Arrays.stream(br.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int result = bfs(map, xy[0], xy[1]);
        
        bw.write(Integer.toString(result));
        bw.flush();
        
        br.close();
        bw.close();

    }

    static int bfs(int[][] map, int n , int m){
        Queue<Node> queue = new LinkedList<>();
        
        int[][] visited = new int[map.length][map[0].length];
        
        queue.add(new Node(1, 0, 0));
        visited[0][0] = 1;
        
        int[][] dydxs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우 순
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(isOut(node, n, m)){
                return node.cost;
            }

            for(int[] dydx : dydxs){
                int adjNodeYPos = node.y + dydx[0];
                int adjNodeXPos = node.x + dydx[1];

                if(adjNodeXPos >= m || adjNodeXPos < 0 || adjNodeYPos >= n || adjNodeYPos < 0){
                    continue;
                }

                if(map[adjNodeYPos][adjNodeXPos] == 1 && visited[adjNodeYPos][adjNodeXPos] == 0){
                    visited[adjNodeYPos][adjNodeXPos] = 1;
                    queue.add(new Node(node.cost + 1, adjNodeXPos, adjNodeYPos));
                }
            }
            
            
            
        }
        
        // 출구를 찾지 못한 경우
        return -1;
    }
    
    static boolean isOut(Node node, int n , int m){
        return node.x == (m-1) && node.y == (n-1);
    }
    
    static class Node{
        int cost;
        int x;
        int y;
        
        
        public Node(int cost, int x, int y){
            this.cost = cost;
            this.x = x;
            this.y = y;
        }
    }

}
