package org.example.ch3;


import java.io.*;
import java.util.*;

public class Problem3A {

    static ArrayList<Integer[]> chickenPos;
    static int n;
    static int m;
    static int[][] map;
    static int result = Integer.MAX_VALUE;

    static Stack<Integer[]> choosed;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        chickenPos = new ArrayList<>(2 * n);
        choosed = new Stack<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    chickenPos.add(new Integer[]{j, i});
                }
            }
        }

        go(0);

        System.out.println(result);
        br.close();
    }


    // 완전 탐색을 통해 가장 작은 치킨 거리를 구하는 함수
    static void go(int startIdx){
        if(choosed.size() == m){
            int chickenDist = calculateCityChickenDist();
            result = Math.min(result, chickenDist);

            return;
        }

        for(int i = startIdx; i < chickenPos.size(); i++){
            choosed.push(chickenPos.get(i));
            go(i + 1);
            choosed.pop();
        }

    }





    // 선택된 치킨집(3번으로 표시됨)을 기반으로 도시의 치킨거리를 측정하는 메서드
    static int calculateCityChickenDist(){
        int result = 0;

        for(int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                if(map[y][x] != 1){
                    continue;
                }
                result += calculateHomeChickenDist(x, y);
            }
        }

        return result;
    }


    // 특정 집과 선택된 치킨집들의 거리 중 가장 작은 값을 반환
    static int calculateHomeChickenDist(int x, int y){
        int min = Integer.MAX_VALUE;
        for(Integer[] chicken : choosed){
            int dist = calculateDist(x, y, chicken[0], chicken[1]);
            min = Math.min(min, dist);
        }
        return min;
    }

    // 두 점의 거리를 측정하는 메서드
    static int calculateDist(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
