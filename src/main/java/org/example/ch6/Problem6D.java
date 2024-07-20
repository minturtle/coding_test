package org.example.ch6;

import java.io.*;
import java.util.*;

public class Problem6D {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int t = Integer.parseInt(br.readLine());

            for(int x = 0; x < t; x++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());

                int[] groupA = new int[n];
                int[] groupB = new int[m];

                st = new StringTokenizer(br.readLine());
                
                for(int i = 0; i < n; i++){
                    groupA[i] = Integer.parseInt(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());

                for(int i = 0; i < m; i++){
                    groupB[i] = Integer.parseInt(st.nextToken());
                }

                bw.write(Integer.toString(execute(groupA, groupB)));
                bw.newLine();
            }
            bw.flush();

        }
    }

    static int execute(int[] groupA, int[] groupB){
        Arrays.sort(groupA);
        Arrays.sort(groupB);

        int result = 0;
        int pt1 = 0;
        int pt2 = 0;


        while(pt1 < groupA.length){
            if(pt2 >= groupB.length){
                result += pt2;
                pt1++;
                continue;
            }    

            pt2 = getSmallIdx(groupB, groupA[pt1], pt2);
            result += pt2;
            pt1++;

        }

        return result;
    }

    private static int getSmallIdx(int[] groupB, int value, int start){
        for(int idx =  start; idx < groupB.length; idx++){
            if(value <= groupB[idx]){
                return idx;
            }

        }

        return groupB.length;
    }
}
