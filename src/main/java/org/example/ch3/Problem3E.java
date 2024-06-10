package org.example.ch3;

import java.io.*;
import java.util.*;

public class Problem3E {

    static List<Integer[]> routes = new ArrayList<>(6);
    static int n;
    static int[] scvs;

    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        scvs = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            scvs[i] = Integer.parseInt(st.nextToken());
        }

        createRoutes();

        simulate(0);

        System.out.println(min);

    }


    static void createRoutes(){
        Integer[] route = new Integer[n];
        for(int i = 0; i < n; i++){
            route[i]= i;
        }

        permutation(0, route);
    }

    static void permutation(int depth, Integer[] route){
        if(depth >= n){
            routes.add(route.clone());
            return;
        }

        for(int i = depth; i < n; i++){
            swap(route, i, depth);
            permutation(depth+1, route);
            swap(route, i, depth);
        }


    }


    static void simulate(int cnt){
        if(cnt >= min){
            return;
        }
        if(isAllDead()){
            min = cnt;
            return;
        }

        for(Integer[] route : routes){
            attack(route);
            simulate(cnt+1);
            back(route);
        }

    }

    static void attack(Integer[] route){
        int[] damages = {9, 3, 1};
        for(int i = 0; i < n; i++){
            scvs[route[i]] -= damages[i];
        }
    }

    static void back(Integer[] route){
        int[] damages = {9, 3, 1};
        for(int i = 0; i < n; i++){
            scvs[route[i]] += damages[i];
        }
    }

    static boolean isAllDead(){
        for(int i = 0; i <n; i++){
            if(scvs[i] > 0){
                return false;
            }
        }

        return true;
    }

    static void swap(Integer[] li, int i1, int i2){
        int temp = li[i1];
        li[i1] = li[i2];
        li[i2] = temp;
    }

}
