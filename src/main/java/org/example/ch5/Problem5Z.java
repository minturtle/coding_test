package org.example.ch5;


import java.io.*;
import java.util.*;

public class Problem5Z {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());

            Line[] lines = new Line[n];
            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                lines[i] = new Line(
                        Long.parseLong(st.nextToken()),
                        Long.parseLong(st.nextToken())
                );
            }

            bw.write(Long.toString(execute(lines)));
            bw.flush();
        }
    }

    static long execute(Line[] lines){
        long result = 0;

        long end = Long.MIN_VALUE;


        Arrays.sort(lines, (l1, l2)->{
            if(l1.start == l2.start){
                return Long.compare(l1.end, l2.end);
            }

            return Long.compare(l1.start, l2.start);
        });

        for(Line line : lines){
            if(end <= line.start){
                result += (line.end - line.start);
                end = line.end;
                continue;
            }
            if(end > line.end){
                continue;
            }


            result += (line.end - end);
            end = line.end;
        }


        return result;
    }


    static class Line{
        public final long start;
        public final long end;

        public Line(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

}
