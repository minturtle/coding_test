package org.example.ch2;

import java.util.*;
import java.io.*;

public class Problem2E {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[][] map = new int[n][n];

        for(int i = 0; i < n; i++){
            map[i] = Arrays.stream(br.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();
        }

        QuadTree qt = new QuadTree(map, n);

        System.out.println(qt.getQuadTreeStr());
    }



    static class QuadTree{

        private final int[][] map;
        private final int n;

        public QuadTree(int[][] map, int n) {
            this.map = map;
            this.n = n;
        }

        public String getQuadTreeStr(){
            return getQuadTreeStr(new Point(0,0), new Point(n, n));
        }

        private String getQuadTreeStr(Point p0, Point p1){
            if(isMapAllSameNum(p0, p1)){
                return Integer.toString(map[p0.y][p0.x]);
            }

            // 4부분으로 map을 나누어 재귀 호출
            int midX = (p0.x + p1.x) / 2;
            int midY = (p0.y + p1.y)/2;

            String str1 = getQuadTreeStr(new Point(p0.x, p0.y), new Point(midX, midY)); // 0,0 ~ 4,4
            String str2 = getQuadTreeStr(new Point(midX, p0.y), new Point(p1.x, midY)); // 4,0 ~ 8,4
            String str3 = getQuadTreeStr(new Point(p0.x, midY), new Point(midX, p1.y)); // 0,4 ~ 4,8
            String str4 = getQuadTreeStr(new Point(midX, midY), new Point(p1.x, p1.y)); // 4,4 ~ 8,8

            StringBuilder sb = new StringBuilder();

            sb
                    .append('(')
                    .append(str1)
                    .append(str2)
                    .append(str3)
                    .append(str4)
                    .append(')');

            return sb.toString();
        }

        private boolean isMapAllSameNum(Point p0, Point p1){
            int num = map[p0.y][p0.x];

            for(int i = p0.y; i < p1.y; i++){
                for(int j = p0.x; j < p1.x; j++){
                    if(map[i][j] != num){
                        return false;
                    }
                }
            }

            return true;
        }


    }


    static class Point{

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int x;
        public int y;
    }


}
