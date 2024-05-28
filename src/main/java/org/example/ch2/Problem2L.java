package org.example.ch2;

import java.io.*;
import java.util.*;


public class Problem2L {


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());

        int ascore = 0;
        int bscore = 0;
        int prev = 0;

        int asum = 0;
        int bsum = 0;



        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int team = Integer.parseInt(st.nextToken());
            int time = convertTimeInt(st.nextToken());

            if(ascore > bscore){
               asum += (time - prev);
            }
            else if(ascore < bscore){
                bsum += (time - prev);
            }

            if(team == 1){
                ascore++;
            }
            else{
                bscore++;
            }

            prev = time;
        }

        if(ascore > bscore){
            asum += (48*60 - prev);
        }
        else if(ascore < bscore){
            bsum += (48*60 - prev);
        }

        bw.write(convertTimeStr(asum));
        bw.newLine();
        bw.write(convertTimeStr(bsum));
        bw.newLine();


        bw.flush();
        br.close();
        bw.close();


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
