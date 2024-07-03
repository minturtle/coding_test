package org.example.ch5;


import java.io.*;
import java.util.*;

public class Problem5A {


    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            if(n == 0){
                bw.write('0');
                bw.flush();
                return;
            }


            Schedule[] schedules = new Schedule[n];

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                schedules[i] = new Schedule(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            bw.write(Integer.toString(execute(schedules)));
            bw.flush();
        }

    }


    static int execute(Schedule[] schedules){
        int result = 0;

        Arrays.sort(schedules, (s1, s2)->{
            if(s1.day == s2.day){
                return s2.money - s1.money;
            }
            return s2.day - s1.day;
        });


        PriorityQueue<Schedule> pq = new PriorityQueue<>((s1, s2)->{
            return s2.money - s1.money;
        });

        int lastDay = schedules[0].day;

        for(int day = lastDay, pt = 0; day > 0 ; day--){
            while(pt < schedules.length && schedules[pt].day >= day){
                pq.add(schedules[pt]);
                pt++;
            }
            if(pq.isEmpty()){
                continue;
            }

            result += pq.poll().money;

        }



        return result;
    }



    static class Schedule{

        public int day;
        public int money;

        public Schedule(int money, int day){
            this.day = day;
            this.money = money;

        }

    }
}
