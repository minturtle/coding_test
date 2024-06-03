package org.example.ch3;


import java.io.*;
import java.util.*;

public class Practice3 {


    static int[][] map;
    static boolean[][] visited;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        map = new int[][]{{10, 20, 21}, {70, 90, 12}, {80, 110, 120}};
        visited = new boolean[3][3];


        visited[0][0] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(map[0][0]);
        go(0, 0, stack);
    }

    static void go(int x , int y, Stack<Integer> stack){
        if(x == 2 && y == 2){
            Iterator<Integer> iterator = stack.iterator();

            while(iterator.hasNext()){
                System.out.print(iterator.next() + ",");
            }
            System.out.println();
        }


        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= 3 || ny < 0 || ny >=3 || visited[ny][nx]){
                continue;
            }

            visited[ny][nx] = true;
            stack.push(map[ny][nx]);
            go(nx, ny, stack);
            visited[ny][nx] = false;
            stack.pop();
        }


    }


}
