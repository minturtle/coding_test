package org.example.ch5;

import java.io.*;
import java.util.*;

public class Practice5 {
}



class Problem1931{

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            int n = Integer.parseInt(br.readLine());
            Meeting[] meetings = new Meeting[n];

            for(int i =0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            bw.write(Integer.toString(execute(meetings)));
            bw.flush();



        }



    }

    static int execute(Meeting[] meetings){
        int result = 0;

        Arrays.sort(meetings, (m1, m2)->{
            if(m1.end == m2.end){
                return m1.start - m2.start;
            }

            return m1.end - m2.end;
        });

        int endTime = meetings[0].end;
        result++;

        for(int i = 1; i < meetings.length; i++){
            if(endTime > meetings[i].start){
                continue;
            }
            result++;
            endTime = meetings[i].end;
        }

        return result;
    }

    static class Meeting{
        public int start;
        public int end;
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}