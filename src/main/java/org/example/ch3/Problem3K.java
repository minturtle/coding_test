package org.example.ch3;


import java.io.*;
import java.util.*;

public class Problem3K {


    public static final int WATER = 0;
    public static final int ICE = 1;
    public static final int SWAN = 2;

    private static final int[] dy = new int[]{-1, 1, 0, 0};
    private static final int[] dx = new int[]{0, 0, -1, 1};

    static int h;
    static int w;
    static int[][] map;
    static boolean[][] visited;

    static int[] swanPos = new int[2];

    static boolean[][] swanVisited;
    static Queue<Integer> swanQueue;


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new int[h][w];

            Queue<Integer> queue = new LinkedList<>();
            visited = new boolean[h][w];


            int flag = 0;

            for(int y = 0; y < h; y++){
                String s = br.readLine().trim();
                for(int x = 0; x < w; x++){

                    map[y][x] = charToInt(s.charAt(x));

                    if(map[y][x] == WATER){
                        queue.add(y * w + x);
                        visited[y][x] = true;
                    }
                    else if(map[y][x] == SWAN){
                        queue.add(y * w + x);
                        swanPos[flag++] = y * w + x;
                    }

                }
            }

            initSwanMap();
            System.out.println(execute(0, queue));
        }


    }


    //BFS를 사용해 얼음을 녹임.
    static int execute(int cnt, Queue<Integer> queue){
        if(isDone()){
            return cnt;
        }
        Queue<Integer> tmp = new LinkedList<>();

        while(!queue.isEmpty()){
            int val = queue.poll();
            int x = val % w;
            int y = val / w;


            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= w || ny < 0 || ny >= h){
                    continue;
                }


                if(visited[ny][nx]){
                    continue;
                }

                if(map[ny][nx] == WATER || map[ny][nx] == SWAN){
                    queue.add(ny * w + nx);
                    visited[ny][nx] = true;
                    continue;
                }


                map[ny][nx] = WATER;
                tmp.add(ny * w + nx);
                visited[ny][nx] = true;
            }
        }



        return execute(cnt + 1, tmp);
    }

    // Swan이 서로 만났는지 확인
    static boolean isDone(){
        Queue<Integer> tmp = new LinkedList<>();

        while(!swanQueue.isEmpty()){
            int val = swanQueue.poll();
            int x = val % w;
            int y = val / w;


            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= w || ny < 0 || ny >= h){
                    continue;
                }
                if(swanVisited[ny][nx]){
                    continue;
                }

                if(map[ny][nx] == WATER){
                    swanQueue.add(ny * w + nx);
                    swanVisited[ny][nx] = true;
                }
                // 백조가 2마리이므로 VISITED 에 의해 무조건 도착지 SWAN임이 보장됨.
                else if(map[ny][nx] == SWAN){
                    return true;
                }
                else{
                    tmp.add(ny * w + nx);
                    swanVisited[ny][nx] = true;
                }
            }


        }

        swanQueue = tmp;
        return false;
    }

    //
    static void initSwanMap(){
        swanQueue = new LinkedList<>();
        swanVisited = new boolean[h][w];


        swanQueue.add(swanPos[0]);
        swanVisited[swanPos[0] / w][swanPos[0] % w] = true;


    }

    static int charToInt(char c){
        switch(c){
            case '.' : return WATER;
            case 'X' : return ICE;
            case 'L' : return SWAN;
        }
        return -1;
    }

}
