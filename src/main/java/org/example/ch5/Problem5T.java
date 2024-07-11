package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5T {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Shark[][] map = new Shark[r][c];

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());

                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int speed = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;

                if(direction < 2){
                    speed = (speed) %(2 * (r - 1)); // (r-1) * 2 만큼 움직이면 어차피 그대로이므로 그거의 나머지만큼만 움직임
                }
                else{
                    speed = (speed) % ( 2 * (c- 1) );
                }
                map[y][x] = new Shark(
                        speed,
                        direction,
                        Integer.parseInt(st.nextToken())
                );


            }

            FishKingSimulator simulator = new FishKingSimulator(map, r, c);

            bw.write(Integer.toString(simulator.simulate()));
            bw.flush();
        }
    }

}

class FishKingSimulator{

    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, 1, -1};

    private final Shark[][] map;

    private final int h;
    private final int w;


    private int fisherPos = -1;

    public FishKingSimulator(Shark[][] map, int r, int c) {
        this.map = map;
        this.h = r;
        this.w = c;
    }

    public int simulate(){
        int result = 0;

        while(fisherPos < w - 1){
            fisherPos++;
            result += catchShark();
            moveSharks();
        }


        return result;
    }


    private int catchShark(){
        int x = fisherPos;
        for(int y = 0; y < h; y++){
            if(map[y][x] == null){
                continue;
            }

            Shark shark = map[y][x];
            map[y][x] = null;
            return shark.size;
        }

        return 0;
    }

    private void moveSharks(){
        Map<Integer, Shark> sharkMap = new HashMap<>();

        for(int y = 0 ; y < h; y++){
            for(int x = 0; x < w; x++){
                if(map[y][x] == null){
                    continue;
                }


                Shark shark = map[y][x];

                int arrivedPos = getArrivedPos(x, y);
                map[y][x] = null;

                if(!sharkMap.containsKey(arrivedPos)){
                    sharkMap.put(arrivedPos, shark);
                    continue;
                }

                if(sharkMap.get(arrivedPos).size < shark.size){
                    sharkMap.put(arrivedPos, shark);
                }

            }
        }

        for(int pos : sharkMap.keySet()){
            int y = pos / w;
            int x = pos % w;

            map[y][x] = sharkMap.get(pos);
        }

    }

    private int getArrivedPos(int x, int y){
        Shark shark = map[y][x];
        int speed = shark.speed;

        int nx;
        int ny;

        while(true){
            nx = x + speed * dx[shark.direction];
            ny = y + speed * dy[shark.direction];

            if(nx >= 0 && nx < w && ny >= 0 && ny < h){
                break;
            }

            if(shark.direction == Shark.DIRECTION_UP){
                speed -= y;
                y = 0;
            }
            else if(shark.direction == Shark.DIRECTION_DOWN){
                speed -= h - 1 -y;
                y = h - 1;
            }
            else if(shark.direction == Shark.DIRECTION_RIGHT){
                speed -= w - 1 -x;
                x = w - 1;
            }
            else{
                speed -= x;
                x = 0;
            }
            shark.reverse();
        }


        return ny * w + nx;

    }


}

class Shark{

    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_LEFT = 3;

    public final int speed;
    public int direction;

    public final int size;



    public Shark(int speed, int direction, int size) {

        this.speed = speed;
        this.direction = direction;
        this.size = size;
    }


    public void reverse(){
        direction ^= 1;
    }
}
