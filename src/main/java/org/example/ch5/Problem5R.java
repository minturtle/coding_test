package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5R {

    public static void main(String[] args) throws IOException{
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int[] numbers = new int[YutGame.TURN_SIZE];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < YutGame.TURN_SIZE; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            YutGame game = new YutGame(numbers);

            bw.write(Integer.toString(game.simulate()));
            bw.flush();

        }
    }

}


class YutGame{


    public static final int TURN_SIZE = 10;

    private static final int[] SCORE_MAP = {
            0, 2, 4, 6, 8,
            10, 12, 14, 16, 18,
            20, 22, 24, 26, 28,
            30, 32, 34, 36, 38,
            40, 13, 16, 19, 25,
            22, 24, 28, 27, 26,
            30, 35, 0
    };

    private static final int[][] graph = {
            {1}, {2}, {3}, {4}, {5},
            {6, 21}, {7}, {8}, {9}, {10},
            {11, 25}, {12}, {13}, {14}, {15},
            {16, 27}, {17}, {18}, {19}, {20},
            {32}, {22}, {23}, {24}, {30},
            {26}, {24}, {28}, {29}, {24},
            {31}, {20}, {32}
    };
    
    private final int[] numbers;
    private final int[] horses;

    public YutGame(int[] numbers) {
        this.numbers = numbers;
        this.horses = new int[4];
    }

    public int simulate(){
        return simulate(0, 0);
    }

    private int simulate(int depth, int score){
        if(depth >= TURN_SIZE){
            return score;
        }

        int maxScore = Integer.MIN_VALUE;
        int moveLength = numbers[depth];
        
        
        for(int choosed = 0; choosed < 4; choosed++){
            int tmpPos = horses[choosed];

            boolean moveSuccessed = move(choosed, moveLength);

            if(!moveSuccessed){
                horses[choosed] = tmpPos;
                continue;
            }

            maxScore = Math.max(simulate(depth + 1, score + SCORE_MAP[horses[choosed]]), maxScore);
            horses[choosed] = tmpPos;
        }

        return maxScore;
    }

    private boolean move(int choosed, int moveLength){



        moveFirst(choosed);
        for(int i = 0; i < moveLength - 1; i++){
            move(choosed);
        }


        return !isAlreadyIn(horses[choosed]);
    }

    private void move(int choosed){
        int[] next = graph[horses[choosed]];

        horses[choosed] = next[0];
    }

    private void moveFirst(int choosed){
        int[] next = graph[horses[choosed]];

        horses[choosed] = next[next.length - 1];
    }

    private boolean isAlreadyIn(int pos){
        if(pos == 32){
            return false;
        }


        int cnt = 0;
        
        for(int i = 0; i < 4; i++){
            if(horses[i] == pos){
                cnt++;
            }
        }
        
        return cnt > 1;
    }
    
    
    

}