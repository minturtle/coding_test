package org.example.ch5;


import java.io.*;
import java.util.*;


public class Problem5M {

    static final int MAX_PLAY_SIZE = 5;

    static final int NORTH = 0;
    static final int SOUTH = 1;
    static final int WEST = 2;
    static final int EAST = 3;

    static int n;

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            n = Integer.parseInt(br.readLine());

            int[][] map = new int[n][n];

            for(int y = 0; y < n; y++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int x = 0; x < n; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] ints = moveNorth(map);

            bw.write(Integer.toString(execute(map, 0)));
            bw.flush();
        }
    }


    static int execute(int[][] map, int depth){
        if(depth == MAX_PLAY_SIZE){
            int max = Integer.MIN_VALUE;

            for(int y = 0; y < n; y++){
                for(int x = 0; x < n; x++){
                    max = Math.max(max, map[y][x]);
                }
            }
            return max;
        }


        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 4; i++){
            int[][] movedMap = move(map, i);
            max = Math.max(execute(movedMap, depth + 1), max);
        }

        return max;
    }

    static int[][] move(int[][] map, int direction){

        switch (direction){
            case EAST:
                return moveEast(map);
            case WEST:
                return moveWest(map);
            case NORTH:
                return moveNorth(map);
            case SOUTH:
                return moveSouth(map);
        }

        return null;
    }

    static int[][] moveEast(int[][] map){
        Deque<Integer> deque = new LinkedList<>();

        int[][] result = new int[n][n];

        for(int y = 0; y < n; y++) {
            int pt = n - 1;

            for (int x = n - 1; x >= 0; x--) {
                int val = map[y][x];
                if(val == 0){
                    continue;
                }
                if(deque.isEmpty() || deque.peekLast() != val){
                    deque.addLast(val);
                    continue;
                }

                deque.removeLast();
                while(!deque.isEmpty()){
                    result[y][pt--] = deque.removeFirst();
                }
                result[y][pt--] = val << 1;
            }

            while(!deque.isEmpty()){
                result[y][pt--] = deque.removeFirst();
            }


        }

        return result;
    }


    static int[][] moveWest(int[][] map){
        Deque<Integer> deque = new LinkedList<>();
        int[][] result = new int[n][n];

        for(int y = 0; y < n; y++) {
            int pt = 0;
            for (int x = 0; x < n; x++) {
                int val = map[y][x];
                if(val == 0){
                    continue;
                }
                if(deque.isEmpty() || deque.peekLast() != val){
                    deque.addLast(val);
                    continue;
                }

                deque.removeLast();
                while(!deque.isEmpty()){
                    result[y][pt++] = deque.removeFirst();
                }
                result[y][pt++] = val << 1;
            }
            while(!deque.isEmpty()){
                result[y][pt++] = deque.removeFirst();
            }

        }

        return result;
    }

    static int[][] moveNorth(int[][] map){
        Deque<Integer> deque = new LinkedList<>();
        int[][] result = new int[n][n];

        for(int x = 0; x < n; x++) {
            int pt = 0;
            for (int y = 0; y < n; y++) {
                int val = map[y][x];
                if(val == 0){
                    continue;
                }
                if(deque.isEmpty() || deque.peekLast() != val){
                    deque.addLast(val);
                    continue;
                }


                deque.removeLast();
                while(!deque.isEmpty()){
                    result[pt++][x] = deque.removeFirst();
                }
                result[pt++][x] = val << 1;
            }

            while(!deque.isEmpty()){
                result[pt++][x] = deque.removeFirst();
            }
        }




        return result;
    }

    static int[][] moveSouth(int[][] map){
        Deque<Integer> deque = new LinkedList<>();
        int[][] result = new int[n][n];

        for(int x = 0; x < n; x++) {
            int pt = n - 1;
            for (int y = n - 1; y >= 0; y--) {
                int val = map[y][x];
                if(val == 0){
                    continue;
                }
                if(deque.isEmpty() || deque.peekLast() != val){
                    deque.addLast(val);
                    continue;
                }

                deque.removeLast();
                while(!deque.isEmpty()){
                    result[pt--][x] = deque.removeFirst();
                }
                result[pt--][x] = val << 1;
            }
            while(!deque.isEmpty()){
                result[pt--][x] = deque.removeFirst();
            }
        }

        return result;

    }



}


