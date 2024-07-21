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


            Set<Integer> setA = new HashSet<>();
            Set<Integer> setB = new HashSet<>();

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++){
                setA.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < m; i++){
                setB.add(Integer.parseInt(st.nextToken()));
            }

            bw.write(Integer.toString(execute(setA, setB)));
            bw.flush();
        }
    }

    private static int execute(Set<Integer> setA, Set<Integer> setB){
        return getChaCount(setA, setB) + getChaCount(setB, setA);
    }

    private static int getChaCount(Set<Integer> set1, Set<Integer> set2){
        HashSet<Integer> set1Copy = new HashSet<>(set1);


        set1Copy.removeAll(set2);
        return set1Copy.size();
    }

}
