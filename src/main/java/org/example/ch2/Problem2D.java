package org.example.ch2;

import java.io.*;
import java.util.*;

public class Problem2D {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        MyMap map = new MyMap(m, n);


        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine().trim());
            int x0 = Integer.parseInt(st.nextToken());
            int y0 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            map.addSquare(x0, y0, x1, y1);
        }

        CountResult result = map.calculate();

        bw.write(Integer.toString(result.count));
        bw.newLine();

        Collections.sort(result.areas);


        for(int area : result.areas){
            bw.write(Integer.toString(area) + " ");
        }

        bw.flush();

        br.close();
        bw.close();
    }


    static class MyMap{

        private int[][] map;
        private int m;
        private int n;
        public MyMap(int m, int n){
            map = new int[m][n];
            this.m = m;
            this.n = n;

        }

        public void addSquare(int x0, int y0, int x1, int y1){
            for(int i = x0; i < x1; i++){
                for(int j = y0; j < y1; j++){
                    map[j][i] = 1;
                }
            }
        }

        public CountResult calculate(){
            int count = 0;
            ArrayList<Integer> resultList = new ArrayList<>(n*m);

            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] != 0){
                        continue;
                    }

                    count++;
                    int area = dfs(j, i);
                    resultList.add(area);
                }
            }

            return new CountResult(count, resultList);
        }

        private int dfs(int x, int y){
            int[] dy = {-1, 1, 0 ,0};
            int[] dx = {0, 0, -1, 1};

            map[y][x] = 2;
            int area = 1;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                    continue;
                }

                if(map[ny][nx] == 0){
                    area += dfs(nx, ny);
                }
            }

            return area;
        }



    }


    static class CountResult{


        public CountResult(int count, ArrayList<Integer> areas){
            this.count = count;
            this.areas = areas;
        }

        private int count;

        private ArrayList<Integer> areas;

    }

}
