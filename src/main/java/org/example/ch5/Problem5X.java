package org.example.ch5;

import java.io.*;
import java.util.*;

public class Problem5X {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            int[][] map = new int[h][w];
            List<CCTV> cctvs = new ArrayList<>(h * w);


            for(int y = 0; y < h; y++){
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x < w; x++){
                    int val = Integer.parseInt(st.nextToken());
                    map[y][x] = val;

                    if(val == CCTVSimulator.WALL || val == CCTVSimulator.SPACE){
                        continue;
                    }

                    cctvs.add(new CCTV(x, y, val));
                }
            }

            bw.write(Integer.toString(execute(map, h, w, cctvs)));
            bw.flush();
        }
    }

    static int execute(int[][] map, int h, int w, List<CCTV> cctvs){

        CCTVSimulator cctvSimulator = new CCTVSimulator(map, h, w, cctvs);

        return cctvSimulator.run();
    }
}

class CCTVSimulator{

    public static final int WALL = 6;
    public static final int SPACE = 0;

    public static final int VIEW = 7;

    private final int[][] map;
    private final int h;
    private final int w;

    private final List<CCTV> cctvs;

    public CCTVSimulator(int[][] map, int h, int w, List<CCTV> cctvs) {

        this.map = map;
        this.h = h;
        this.w = w;
        this.cctvs = cctvs;
    }


    public int run(){
        return permutation(0);
    }


    private int permutation(int depth){
        if(depth == cctvs.size()){
            view();
            int result = getSpace();
            removeView();

            return result;
        }


        int min = Integer.MAX_VALUE;


        CCTV cctv = cctvs.get(depth);
        if(cctv.getNumber() == 5){
            return permutation(depth + 1);
        }

        for(int i = 0; i < 4; i++){
            cctv.setDirection(i);
            int res = permutation(depth + 1);
            min = Math.min(res, min);
        }

        return min;
    }


    private void view(){
        for(CCTV cctv : cctvs){
            int viewDirection = cctv.getViewDirection();

            if(cctv.getY() != 0 && isBitOn(viewDirection, CCTV.DIRECTION_UP)){
                setViewUp(cctv);
            }
            if(cctv.getY() != (h-1) && isBitOn(viewDirection, CCTV.DIRECTION_DOWN)){
                setViewDown(cctv);
            }
            if(cctv.getX() != 0 && isBitOn(viewDirection, CCTV.DIRECTION_LEFT)){
                setViewLeft(cctv);
            }
            if(cctv.getX() != w - 1 && isBitOn(viewDirection, CCTV.DIRECTION_RIGHT)){
                setViewRight(cctv);
            }
        }
    }

    private int getSpace(){
        int result = 0;
        for(int y= 0; y < h; y++){
            for(int x = 0 ; x < w; x++){
                if(map[y][x] != SPACE){
                    continue;
                }
                result++;
            }
        }

        return result;
    }

    private void removeView(){
        for(int y= 0; y < h; y++){
            for(int x = 0 ; x < w; x++){
                if(map[y][x] != VIEW){
                    continue;
                }
                map[y][x] = SPACE;
            }
        }

    }

    private void setViewUp(CCTV cctv){
        int x = cctv.getX();
        for(int y = cctv.getY() - 1; y >= 0; y--){
            if(map[y][x] == WALL){
                return;
            }

            if(map[y][x] != SPACE){
                continue;
            }
            map[y][x] = VIEW;
        }

    }

    private void setViewDown(CCTV cctv){
        int x = cctv.getX();
        for(int y = cctv.getY() + 1; y < h; y++){
            if(map[y][x] == WALL){
                return;
            }

            if(map[y][x] != SPACE){
                continue;
            }
            map[y][x] = VIEW;
        }
    }

    private void setViewLeft(CCTV cctv){
        int y = cctv.getY();
        for(int x = cctv.getX() - 1; x >= 0; x--){
            if(map[y][x] == WALL){
                return;
            }

            if(map[y][x] != SPACE){
                continue;
            }
            map[y][x] = VIEW;
        }
    }
    private void setViewRight(CCTV cctv){
        int y= cctv.getY();
        for(int x = cctv.getX() + 1; x < w; x++){
            if(map[y][x] == WALL){
                return;
            }

            if(map[y][x] != SPACE){
                continue;
            }
            map[y][x] = VIEW;
        }
    }



    private boolean isBitOn(int viewDirection, int direction){
        return (viewDirection & direction) != 0;
    }


}


class CCTV{

    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_UP = 8;


    private final int x;
    private final int y;

    private final int number;

    private int direction;

    public CCTV(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getViewDirection(){


        int value = getDefault();
        if(direction == 0){
            return value;
        }

        return ((value << direction) & 0b1111) | ((value >> (4 - direction) & 0b1111));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int getDefault(){
        switch (number){
            case 1 : return 1;
            case 2 : return 5;
            case 3 : return 9;
            case 4 : return 13;
            default:
                return 15;
        }
    }
}