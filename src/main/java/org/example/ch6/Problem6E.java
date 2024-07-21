package org.example.ch6;

import java.io.*;
import java.util.*;


public class Problem6E {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] setA = new int[n];
            int[] setB = new int[m];

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++){
                setA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < m; i++){
                setB[i] = Integer.parseInt(st.nextToken());
            }
            
            bw.write(Integer.toString(execute(setA, setB)));
            bw.flush();
        }
    }

    private static int execute(int[] setA, int[] setB){
        Arrays.sort(setA);
        Arrays.sort(setB);

        return getChaCount(setA, setB) + getChaCount(setB, setA);
    }

    private static int getChaCount(int[] setA, int[] setB){
        int result = 0;
        for(int number : setA){
            if(!binarySearch(setB, number)){
                result++;
            }
        }

        return result;
    }

    private static boolean binarySearch(int[] sortedGroup, int value){
        int l = 0, r = sortedGroup.length;

        while(l < r){
            int mid = (l + r) / 2;
            if(sortedGroup[mid] == value){
                return true;
            }
            if(sortedGroup[mid] > value){
                r = mid;
                continue;
            }

            l = mid + 1;
        }

        return false;
    }

}
