package org.example.ch1;

import java.io.*;
import java.util.*;

public class Problem1H {


    static Dict dict;

    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dict = new Dict(nm[0]);


        // 도감은 1번부터 사용
        for(int i = 1; i <= nm[0]; i++){
            dict.add(br.readLine().trim(), i);
        }

        for(int i = 0; i < nm[1]; i++){
            String question = br.readLine().trim();
            if(isNumber(question)){
                bw.write(dict.find(Integer.parseInt(question)));
            }
            else{
                bw.write(dict.find(question));
            }
            bw.newLine();
        }


        bw.flush();

        br.close();
        bw.close();
    }



    static boolean isNumber(String input){
        try{
            int x = Integer.parseInt(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }




    static class Dict{
        private String[] dict;

        public Dict(int size){
            dict = new String[size + 1];
        }

        public void add(String name, int idx){
            dict[idx] = name;
        }

        public String find(int idx){
            return dict[idx];
        }

        public String find(String name){
            for(int i = 1; i < dict.length; i++){
                if(!dict[i].equals(name)){
                    continue;
                }
                return Integer.toString(i);
            }

            return "-1";
        }

    }

}
