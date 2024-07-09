package org.example.ch5;

import java.io.*;
import java.util.*;

public class Problem5Q {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n, l;

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            Water[] waters = new Water[n];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                waters[i] = new Water(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            Arrays.sort(waters, (w1, w2)->{
                return w1.start - w2.start;
            });

            bw.write(Integer.toString(execute(waters, l)));
            bw.flush();
        }
    }


    static int execute(Water[] waters, int l){
        int result = 0;

        int lastBridgeIdx = 0;

        for(Water water : waters){

            if(lastBridgeIdx < water.start){
                lastBridgeIdx = water.start;
            }

            int bridgeCnt = (int)Math.ceil((double)(water.end - lastBridgeIdx) / (double) l);
            result += bridgeCnt;


            lastBridgeIdx += bridgeCnt * l;
        }


        return result;
    }
}


class Water{
    public final int start;
    public final int end;

    public Water(int start, int end) {
        this.start = start;
        this.end = end;
    }

}