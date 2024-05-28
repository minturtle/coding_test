package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2L {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());

        Team[] teams = {new Team(), new Team()};


        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int team = Integer.parseInt(st.nextToken());
            int time = convertTimeInt(st.nextToken());

            teams[team-1].addScore(time);
        }

        teams[0].endGame();
        teams[1].endGame();

        int team1ScoreWinSec = 0; //team1이 이긴 초
        int team2ScoreWinSec = 0; // team2가 이긴 초

        for(int i = 0; i < 48 * 60; i++){
            if(teams[0].scoreMap[i] > teams[1].scoreMap[i]){
                team1ScoreWinSec++;
            }
            else if(teams[0].scoreMap[i] < teams[1].scoreMap[i]){
                team2ScoreWinSec++;
            }
        }

        bw.write(convertTimeStr(team1ScoreWinSec));
        bw.newLine();
        bw.write(convertTimeStr(team2ScoreWinSec));
        bw.newLine();


        bw.flush();
        br.close();
        bw.close();


    }

    static class Team{

        private int[] scoreMap = new int[48 * 60];

        public void addScore(int time){
            scoreMap[time] = 1;
        }

        public void endGame(){
            int tmp = 0;
            for(int i = 0; i < 48 * 60; i++){
                if(scoreMap[i] == 1){
                    tmp++;
                }
                scoreMap[i] = tmp;
            }
        }

    }


    static int convertTimeInt(String time){
        StringTokenizer st = new StringTokenizer(time, ":");

        int min = Integer.parseInt(st.nextToken());
        int sec = Integer.parseInt(st.nextToken());

        return min * 60 + sec;
    }

    static String convertTimeStr(int time){
        int min = time / 60;
        int sec = time % 60;

        return String.format("%02d:%02d", min, sec);

    }
}
