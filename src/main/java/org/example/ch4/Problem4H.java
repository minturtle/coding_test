package org.example.ch4;


import java.io.*;
import java.util.*;


public class Problem4H {

    static int h, w;

    static int[][] map;

    static int[][] visited;
    static int cnt;
    static int result2 = Integer.MIN_VALUE;

    static int result3 = Integer.MIN_VALUE;

    static int[] dx = new int[]{-1, 0, 1, 0}; //서 북 동 남
    static int[] dy = new int[]{0, -1, 0 , 1};

    static Map<Integer, Integer> roomSizeMap = new HashMap<>();


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[h][w];
            visited = new int[h][w];


            for(int y = 0; y < h; y++){
                st = new StringTokenizer(br.readLine().trim());
                for(int x = 0; x < w; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }


            getResult1And2();
            getResult3();
            bw.write(Integer.toString(cnt));
            bw.newLine();

            bw.write(Integer.toString(result2));
            bw.newLine();

            bw.write(Integer.toString(result3));

            bw.flush();
        }
    }




    static void getResult1And2(){
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                if(visited[y][x] != 0){
                    continue;
                }
                cnt++;
                int dfsResult = dfs(x, y);
                roomSizeMap.put(cnt, dfsResult);
                result2= Math.max(dfsResult, result2);
            }
        }
    }


    static void getResult3(){
        for(int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if(x + 1 < w && visited[y][x] != visited[y][x + 1]){
                    int result = addRoomIfAvailable(x, y, x + 1, y, 2);

                    result3 = Math.max(result, result3);
                }
                if(y + 1 < h && visited[y][x] != visited[y + 1][x]){
                    int result = addRoomIfAvailable(x, y, x, y+1, 3);
                    result3 = Math.max(result, result3);
                }
            }
        }
    }



    private static int dfs(int x, int y){
        visited[y][x] = cnt;
        int size = 1;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isWall(map[y][x], i)){
                continue;
            }
            if(nx < 0 || nx >= w || ny < 0 || ny >= h || visited[ny][nx] != 0){
                continue;
            }

            size += dfs(nx, ny);
        }

        return size;
    }


    private static boolean isWall(int value, int direction){
        return (value & ( 1 << direction)) != 0;
    }


    private static int addRoomIfAvailable(int x1, int y1, int x2, int y2, int direction){
        if(!isWall(map[y1][x1], direction)){
            return -1;
        }

        int roomIdx1 = visited[y1][x1];
        int roomIdx2 = visited[y2][x2];

        return roomSizeMap.get(roomIdx1) + roomSizeMap.get(roomIdx2);
    }

}
