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
    public static final int END_POS = 41;


    private static final List<Integer>[] specialLines = new List[]{
            List.of(10, 13, 16, 19, 25, 30, 35, 40),
            List.of(20, 22, 24, 25, 30, 35, 40),
            List.of(30, 28, 27, 26, 25, 30, 35, 40)
    };


    private final int[] numbers;
    private final int[] posList;
    private final int[] routeNumber;

    public YutGame(int[] numbers) {
        this.numbers = numbers;
        this.posList = new int[4];
        this.routeNumber = new int[]{-1, -1, -1, -1};
    }

    public int simulate(){
        return simulate(0, 0);
    }

    private int simulate(int depth, int score){
        if(depth >= TURN_SIZE){
            return score;
        }

        int maxScore = Integer.MIN_VALUE;

        for(int choosed = 0; choosed < 4; choosed++){
           // choosed : 움직일 말의 idx
            int tmp = posList[choosed];
            int tmpRouteNum = routeNumber[choosed];

            if(tmp == END_POS){
                continue;
            }

            int moveLength = numbers[depth];
            int addedScore = move(choosed, moveLength);

            if(addedScore == -1){
                continue;
            }


            int finalScore = simulate(depth + 1, score + addedScore);

            maxScore = Math.max(maxScore, finalScore);
            posList[choosed] = tmp;
            routeNumber[choosed] = tmpRouteNum;
        }

        return maxScore;
    }

    private int move(int choosed, int moveLength){
        int currentPos = posList[choosed];
        int nPos;

        int specialLineIdx = routeNumber[choosed];

        if(specialLineIdx == -1){
            nPos = currentPos + (moveLength * 2);
        }
        else{
            nPos = moveSpecialPos(currentPos, moveLength, specialLineIdx);
        }

        if(nPos >= END_POS){
            posList[choosed] = END_POS;
            return 0;
        }


        if(nPos != 0 && nPos != 40 && nPos % 10 == 0){
            routeNumber[choosed] = (nPos / 10) -1;
        }


        for(int i = 0; i < 4; i++){
            if(posList[i] == nPos && routeNumber[i] == routeNumber[choosed]){
                return -1;
            }
        }


        posList[choosed] = nPos;
        return nPos;
    }

    int moveSpecialPos(int currentPos, int moveLength, int specialLineIdx){


        List<Integer> line = specialLines[specialLineIdx];

        int idx = line.indexOf(currentPos);

        if(idx == -1){
            return -1;
        }

        int nIdx = idx + moveLength;

        if(nIdx >= line.size()){
            return END_POS;
        }

        return line.get(nIdx);


    }

}