package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5D {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            Cow[] cows = new Cow[n];

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            bw.write(Integer.toString(execute(cows)));
            bw.flush();

        }
    }

    static int execute(Cow[] cows){
        int time = 1;

        Arrays.sort(cows, (c1, c2)->{
            if(c1.eta == c2.eta){
                return c1.executeTime - c2.executeTime;
            }
            return c1.eta - c2.eta;
        });

        // 현재 시간에 뽑을 수 있는 소들의 검문시간
        Queue<Integer> timeQueue = new LinkedList<>();

        for(int pt = 0; pt < cows.length; ){
            while(pt < cows.length && cows[pt].eta <= time){
                timeQueue.add(cows[pt].executeTime);
                pt++;
            }

            if(timeQueue.isEmpty()){
                time++;
                continue;
            }

            while(!timeQueue.isEmpty()){
                time += timeQueue.poll();
            }
        }

        return time;
    }



    static class Cow{
        public int eta;
        public int executeTime;

        public Cow(int eta, int executeTime) {
            this.eta = eta;
            this.executeTime = executeTime;
        }
    }
}
