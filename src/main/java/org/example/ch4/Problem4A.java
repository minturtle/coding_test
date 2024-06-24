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


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            n = Integer.parseInt(br.readLine());
            map = new int[n][5];


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

            execute();


            if(min == Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }

            System.out.println(min);
            System.out.println(result);
        }
    }


    static void execute(){
        for(int visited = 0; visited < (1 << n); visited++){
            if(!isOk(visited)){
                continue;
            }
            int price = calculatePrice(visited);
            if(price > min){
                continue;
            }
            if(price == min){
                String str = createResultString(visited);
                result = result.compareTo(str) > 0 ? str : result;
                continue;

            }
            min = price;
            result = createResultString(visited);
        }
    }

    static boolean isOk(int visited){
        int protein = 0, fat = 0, carbs = 0, vitamin = 0;

        for(int i = 0; i < n; i++){
            int isVisit = visited & (1 << i);

            if(isVisit == 0){
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

    static int calculatePrice(int visited){
        int price = 0;
        for(int i = 0; i < n; i++){
            int isVisit = visited & (1 << i);
            if(isVisit == 0){
                continue;
            }
            price+= map[i][PRICE_IDX];
        }
        return price;
    }

    static String createResultString(int visited){
        StringJoiner sj = new StringJoiner(" ");

        for(int i = 0; i < n; i++){
            int isVisit = (visited & (1 << i));
            if(isVisit == 0){
                continue;
            }
            sj.add(Integer.toString(i + 1));
        }

        return sj.toString();
    }
}
