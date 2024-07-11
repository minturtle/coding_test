package org.example.ch5;


import java.io.*;
import java.util.*;

public class Problem5U {


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            DragonSimulator simulator = new DragonSimulator();

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());

                simulator.createDragonCurve(x, y, d, g);
            }


            bw.write(Integer.toString(simulator.getResult()));
            bw.flush();
        }


    }

}

class DragonSimulator{

    private final List<DragonCurve> dragonCurveList = new LinkedList<>();


    public void createDragonCurve(int x, int y, int direction, int generation){
        DragonCurve dragonCurve = new DragonCurve(x, y, direction);

        for(int i = 0; i < generation; i++){
            dragonCurve.next();
        }

        dragonCurveList.add(dragonCurve);
    }

    public int getResult(){
        boolean[][] map = new boolean[101][101];
        int result = 0;

        for(DragonCurve dragonCurve : dragonCurveList){
            for(DragonCurve.Point point : dragonCurve.pointInfo){
                if(point.x < 0 || point.x > 100 || point.y < 0 || point.y > 100){
                    continue;
                }

                map[point.y][point.x] = true;
            }
        }

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                int[][] squarePoints = {
                        {j, i}, {j + 1, i},
                        {j, i + 1}, {j + 1, i+ 1}
                };

                if(isSquareInDragonCurve(map, squarePoints)){
                    result++;
                }
            }
        }


        return result;
    }

    private boolean isSquareInDragonCurve(boolean[][] map, int[][] squarePoints){

        for(int[] point : squarePoints){
            if(!map[point[1]][point[0]]){
                return false;
            }
        }

        return true;
    }


}

class DragonCurve{

    public static final int[] dy = {0, -1, 0, 1};
    public static final int[] dx = {1, 0, -1, 0};


    public final List<Point> pointInfo = new LinkedList<>();

    public DragonCurve(int x, int y, int direction) {

        pointInfo.add(new Point(x, y));

        int nx = x + dx[direction];
        int ny = y + dy[direction];

        pointInfo.add(new Point(nx, ny));
    }

    public void next(){
        LinkedList<Point> copied = new LinkedList<>(pointInfo);
        Collections.reverse(copied);
        Queue<Point> reversed = copied;

        Point centerPoint = reversed.poll();
        for(Point p : reversed){
            int nx = centerPoint.x + (centerPoint.y - p.y);
            int ny = centerPoint.y + (p.x - centerPoint.x);

            Point newPoint = new Point(nx, ny); //평행이동한 점의 좌표

            pointInfo.add(newPoint);

        }
    }

    static class Point{
        public final int x;
        public final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof Point)){
                return false;
            }
            Point p = (Point) o;

            return p.x == this.x && p.y == this.y;
        }
    }

}
