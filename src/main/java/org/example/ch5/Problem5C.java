package org.example.ch5;

import java.io.*;
import java.util.*;

public class Problem5C {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

        int n = Integer.parseInt(br.readLine());

        Problem[] problems = new Problem[n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            problems[i] = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        bw.write(Integer.toString(execute(problems)));
        bw.flush();
        }
    }

    static int execute(Problem[] problems){
        int result = 0;

        Arrays.sort(problems, (p1, p2)->{
            if(p1.deadline == p2.deadline){
                return p2.reward - p1.reward;
            }
            return p2.deadline - p1.deadline;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2)->{
            return i2 - i1;
        });

        int lastTime = problems[0].deadline;

        for(int time = lastTime, pt = 0; time > 0; time--){
            while(pt < problems.length && problems[pt].deadline >= time){
                pq.add(problems[pt].reward);
                pt++;
            }
            if(pq.isEmpty()){
                continue;
            }

            result += pq.poll();
        }



        return result;
    }



    static class Problem{
        public int deadline;
        public int reward;


        public Problem(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }
    }
}
