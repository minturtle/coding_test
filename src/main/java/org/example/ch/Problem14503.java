package org.example.ch;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

public class Problem14503 {

    private static int[][] map;
    private static int n;
    private static int m;
    private static Robot robot;

    private static int CLEANED_SPACE = -1;
    private static int SPACE = 0;
    private static int WALL = 1;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n][m];

            st = new StringTokenizer(br.readLine());
            Direction.init();

            robot = new Robot(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(Integer.toString(execute()));
            bw.flush();
        }
    }

    private static int execute(){
        int result = 0;

        while(true){
            if(robot.isCurrentSpaceNotCleaned()){
                robot.clean();
                result++;
            }
            if(!robot.checkAround()){
                boolean moveResult = robot.moveBackward();
                if(!moveResult){
                    break;
                }
                continue;
            }
            robot.turn();
            robot.moveIfAvailable();

        }

        return result;
    }

    private static class Robot{
        public int x;
        public int y;
        public Direction direction;

        public Robot(int y, int x, int direction) {
            this.x = x;
            this.y = y;
            this.direction = Direction.of(direction);
        }

        public boolean isCurrentSpaceNotCleaned(){
            return map[y][x] == SPACE;
        }

        public void clean(){
            map[y][x] = CLEANED_SPACE;
        }

        public boolean checkAround(){
            for(Direction direction : Direction.values()){
                int nx = x + direction.dx;
                int ny = y + direction.dy;

                if(nx < 0 || nx >= m || ny < 0 || ny >= n){
                    continue;
                }
                if(map[ny][nx] == SPACE){
                    return true;
                }
            }
            return false;
        }

        public void turn(){
            direction = direction.next;
        }

        public void moveIfAvailable(){
            int nx = x + direction.dx;
            int ny = y + direction.dy;

            if(nx < 0 || nx >= m || ny < 0 || ny >= n){
                return;
            }
            if(map[ny][nx] != SPACE){
                return;
            }
            x = nx;
            y = ny;
        }

        public boolean moveBackward(){
            Direction backward = direction.next.next;

            int nx = x + backward.dx;
            int ny = y + backward.dy;

            if(nx < 0 || nx >= m || ny < 0 || ny >= n){
                return false;
            }
            if(map[ny][nx] == WALL){
                return false;
            }
            x = nx;
            y = ny;

            return true;
        }
    }

    private enum Direction{

        UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

        private Direction next;
        private int dx;
        private int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public static void init(){
            UP.next = LEFT;
            LEFT.next = DOWN;
            DOWN.next = RIGHT;
            RIGHT.next = UP;
        }

        static Direction of(int val){
            switch (val){
                case 0 : return UP;
                case 1 : return RIGHT;
                case 2 : return DOWN;
                case 3 : return LEFT;
                default: throw new RuntimeException();
            }
        }
    }
}
