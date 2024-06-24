package org.example.ch4;


import java.io.*;
import java.util.*;

public class Problem4A {



    static final int PROTEIN_IDX = 0;
    static final int FAT_IDX = 1;
    static final int CARBS_IDX = 2;
    static final int VITAMIN_IDX = 3;
    static final int PRICE_IDX = 4;

    static int n;
    static int[][] map;

    static int[] leastConditions = new int[4];

    static int min  = Integer.MAX_VALUE;
    static String result;

    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            n = Integer.parseInt(br.readLine());
            map = new int[n][5];
            visited = new boolean[n];


            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < 4; i++){
                leastConditions[i] = Integer.parseInt(st.nextToken());
            }


            for(int i = 0; i< n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 5; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            execute(0);


            if(min == Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }
            System.out.println(min);
            System.out.println(result);
        }
    }


    static void execute(int i){
        if(i == n){
            int price = calculatePrice();
            if(!isOk() || (min < price)) {
                return;
            }
            if(min == price){
                String tmp = createResult();
                result = result.compareTo(tmp) > 0 ? tmp : result;
                return;
            }


            min = price;
            result = createResult();
            return;
        }


        for(int x = 0; x < 2; x++){
            visited[i] = x == 0;
            execute(i + 1);
        }


    }

    static boolean isOk(){
        int protein = 0;
        int fat = 0;
        int carbs = 0;
        int vitamin = 0;

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                continue;
            }

            protein += map[i][PROTEIN_IDX];
            fat += map[i][FAT_IDX];
            carbs += map[i][CARBS_IDX];
            vitamin += map[i][VITAMIN_IDX];
        }

        return protein >= leastConditions[PROTEIN_IDX] &&
                fat >= leastConditions[FAT_IDX] &&
                carbs >= leastConditions[CARBS_IDX] &&
                vitamin >= leastConditions[VITAMIN_IDX];
    }


    static int calculatePrice(){
        int price = 0;

        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                continue;
            }
            price += map[i][PRICE_IDX];
        }
        return price;
    }

    static String createResult(){
        StringJoiner sj = new StringJoiner(" ");

        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                continue;
            }
            sj.add(Integer.toString(i + 1));
        }
        return sj.toString();
    }



}
