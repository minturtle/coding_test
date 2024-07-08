package org.example.ch5;


import java.io.*;
import java.util.*;


public class Problem5N {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];

            int k = Integer.parseInt(br.readLine());

            for(int i = 0; i < k; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;

                map[y][x] = Game.APPLE;
            }


            int l = Integer.parseInt(br.readLine());

            Queue<TurnInfo> turnInfos = new LinkedList<>();


            for(int i = 0; i < l; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                turnInfos.add(new TurnInfo(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
            }

            Game game = new Game(map);
            int time = 0;

            while(true){
                time++;
                boolean isContinue = game.move();

                if(!isContinue){
                    break;
                }

                if(turnInfos.isEmpty() || turnInfos.peek().time != time){
                    continue;
                }

                TurnInfo turnInfo = turnInfos.poll();

                if(turnInfo.direct == 'L'){
                    game.turnLeft();
                }
                else{
                    game.turnRight();
                }
            }

            bw.write(Integer.toString(time));
            bw.flush();
        }
    }

    static class TurnInfo{
        public int time;
        public char direct;


        public TurnInfo(int time, char direct) {
            this.time = time;
            this.direct = direct;
        }
    }


}


class Game{

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;


    private static final int SPACE = 0;
    public static final int APPLE = 1;
    private static final int SNAKE = 2;


    private Deque<Integer> snakeDeque = new LinkedList<>();

    private int[][] map;
    private int n;

    private int direction = EAST;


    public Game(int[][] map){
        this.map = map;
        this.n = map.length;

        map[0][0] = SNAKE;
        snakeDeque.addFirst(0);
    }

    public void turnLeft(){
        if(direction == NORTH){
            direction = WEST;
            return;
        }

        direction--;
    }

    public void turnRight(){
        direction = (direction + 1) % 4;
    }


    public boolean move(){

        int npos = getNPos();

        int nx = npos % n;
        int ny = npos / n;

        if(npos == -1|| map[ny][nx] == SNAKE){
            return false;
        }

        if(map[ny][nx] != APPLE){
            int tailPos = snakeDeque.removeLast();
            int tailY = tailPos / n;
            int tailX = tailPos % n;

            map[tailY][tailX] = SPACE;
        }

        map[ny][nx] = SNAKE;
        snakeDeque.addFirst(npos);

        return true;
    }

    private int getNPos(){
        Integer headPos = snakeDeque.peekFirst();

        int headX = headPos % n;
        int headY = headPos / n;

        switch (direction){
            case NORTH :
                if(headY == 0){
                    return -1;
                }

                return (headY - 1) * n + headX;
            case SOUTH:
                if(headY == n - 1){
                    return -1;
                }

                return (headY + 1) * n + headX;
            case EAST:
                if(headX == n - 1){
                    return -1;
                }

                return headY * n + headX + 1;
            case WEST:
                if(headX == 0){
                    return -1;
                }

                return headY * n + headX - 1;
        }

        return -1;
    }
}