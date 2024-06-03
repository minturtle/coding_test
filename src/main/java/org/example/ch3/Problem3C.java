package org.example.ch3;

import java.io.*;
import java.util.*;

public class Problem3C {

    static int cnt = 0;
    static int n;

    static int l;
    static int r;

    static int[][] map;


    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int y = 0; y < n; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < n; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            List<List<Integer[]>> unites = getUnites();

            if(unites.isEmpty()){
                break;
            }

            for(List<Integer[]> unite : unites){
                go(unite);
            }

            cnt++;
        }

        System.out.println(cnt);
    }



    /**
     * Unites를 반환하는 메서드
     * Unites는 특정 좌표의 2차원 배열로 구성되어 있으며, Unites 내의 배열에 속한 좌표는 모두 한 Unite에 속한다.
     * */
    static List<List<Integer[]>> getUnites(){
        List<List<Integer[]>> result = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        for(int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                List<Integer[]> unite = dfs(visited, x, y);
                if(unite.size() == 1){
                    continue;
                }
                result.add(unite);
            }
        }


        return result;
    }


    static void go(List<Integer[]> unite){
        int value = unite.stream().mapToInt(p->map[p[1]][p[0]]).sum() / unite.size();

        unite.forEach(p -> map[p[1]][p[0]] = value);
    }


    static List<Integer[]> dfs(boolean[][] visited, int x, int y){
        List<Integer[]> unite = new ArrayList<>();
        unite.add(new Integer[]{x, y});

        visited[y][x] = true;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[ny][nx]){
                continue;
            }

            int temp = Math.abs(map[y][x] - map[ny][nx]);
            if(temp < l || temp > r){
                continue;
            }


            List<Integer[]> subUnite = dfs(visited, nx, ny);
            unite.addAll(subUnite);
        }


        return unite;
    }

}
