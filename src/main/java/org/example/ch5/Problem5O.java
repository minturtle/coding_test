package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5O {

    static Simulator simulator;
    static int[] seqArr;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int h, w, k;

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            int[][] map = new int[h][w];

            for(int y = 0; y < h; y++){
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x < w; x++){
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] rcsList = new int[k][3];

            for(int i = 0; i < k; i++){
                rcsList[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            simulator = new Simulator(h, w, map, rcsList);


            seqArr = new int[k];
            for(int i = 0; i < k; i++){
                seqArr[i] = i;
            }

            bw.write(Integer.toString(execute(0)));
            bw.flush();
        }
    }

    static int execute(int startIdx){
        if(startIdx == seqArr.length){

            for(int num : seqArr){
                simulator.turn(num);
            }

            return simulator.end();
        }

        int min = Integer.MAX_VALUE;

        for(int i = startIdx; i < seqArr.length; i++){
            swap(startIdx, i);
            min = Math.min(execute(startIdx + 1), min);
            swap(startIdx, i);
        }

        return min;
    }

    static void swap(int i1, int i2){
        int tmp = seqArr[i1];
        seqArr[i1] = seqArr[i2];
        seqArr[i2] = tmp;
    }

}

class Simulator{

    private final int[] dy = new int[]{0, 1, 0, -1};
    private final int[] dx = new int[]{1, 0, -1, 0};


    private final int h;
    private final int w;
    private final int[][] map;

    private int[][] simulateMap;

    private final TurnInfo[] turnInfoList;

    public Simulator(int h, int w, int[][] map, int[][] rcsList) {
        this.h = h;
        this.w = w;
        this.map = map;

        this.turnInfoList = Arrays.stream(rcsList).map(TurnInfo::new).toArray(TurnInfo[]::new);
    }


    public void turn(int idx){
        if(simulateMap == null){
            initSimulateMap();
        }

        Point start = turnInfoList[idx].start;
        Point end = turnInfoList[idx].end;

        while(start.compareTo(end) < 0){
            turnLine(start, end);

            start = new Point(start.x + 1, start.y + 1);
            end = new Point(end.x - 1, end.y - 1);
        }

    }


    public int end(){
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < h; i++){
            min = Math.min(min, getRowValue(i));
        }
        this.simulateMap = null;
        return min;
    }

    private void initSimulateMap(){
        simulateMap = new int[h][w];

        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                simulateMap[y][x] = map[y][x];
            }
        }
    }

    private int getRowValue(int row){
        int result = 0;
        for(int x = 0; x < w; x++){
            result += simulateMap[row][x];
        }
        return result;
    }

    private void turnLine(Point start, Point end){
        int[] tmp = new int[(end.x - start.x + 1) * 4];
        int direction = 0;
        int pt = 0;

        tmp[pt++] = simulateMap[start.y][start.x];
        int x = start.x + dx[direction];
        int y = start.y + dy[direction];

        while(x != start.x || y != start.y){
            tmp[pt++] = simulateMap[y][x];
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(nx < start.x || nx > end.x || ny < start.y || ny > end.y){
                direction++;
                nx = x + dx[direction];
                ny = y + dy[direction];
            }
            x = nx;
            y = ny;
        }


        direction = 0;
        simulateMap[start.y][start.x] = tmp[pt-1];
        x = start.x + dx[direction];
        y = start.y + dy[direction];

        for(int pt2 = 0; pt2 < pt; pt2++){
            simulateMap[y][x] = tmp[pt2];

            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(nx == start.x && ny == start.y){
                break;
            }

            if(nx < start.x || nx > end.x || ny < start.y || ny > end.y){
                direction++;
                nx = x + dx[direction];
                ny = y + dy[direction];
            }
            x = nx;
            y = ny;
        }
    }
}

class TurnInfo{

    public final Point start;
    public final Point end;


    public TurnInfo(int[] rcs) {
        this.start = new Point(rcs[1] - rcs[2] - 1, rcs[0] - rcs[2] - 1);
        this.end = new Point(rcs[1] + rcs[2] - 1,rcs[0] + rcs[2] - 1);
    }
}

class Point implements Comparable{
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        Point c = (Point)o;

        if(this.x > c.x || this.y > c.y){
            return 1;
        }
        else if(this.x == c.x && this.y == c.y){
            return 0;
        }

        return -1;
    }
}