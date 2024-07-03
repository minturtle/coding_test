package org.example.ch5;

import java.io.*;
import java.util.*;

public class Problem5B {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

            String st = br.readLine().trim();
            String boomSt = br.readLine().trim();


            Optional<String> result = execute(st, boomSt);


            bw.write(result.orElseGet(()->"FRULA"));
            bw.flush();

        }
    }


    static Optional<String> execute(String str, String boomStr){
        char[] cArr = new char[str.length() + 1];
        int pt = 1;
        char lastBoomChar = boomStr.charAt(boomStr.length() - 1);

        loop1 : for(int i = 0; i < str.length(); i++){
            cArr[pt] = str.charAt(i);

            if(cArr[pt] != lastBoomChar || pt < boomStr.length()){
                pt++;
                continue;
            }

            for(int j = 0; j < boomStr.length() ;j++){
                int cArrIdx = pt - boomStr.length() + j + 1;
                if(boomStr.charAt(j) != cArr[cArrIdx]){
                    pt++;
                    continue loop1;
                }
            }
            pt -= boomStr.length();
            pt++;

        }

        if(pt <= 1){
            return Optional.empty();
        }


        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < pt; i++){
            sb.append(cArr[i]);
        }

        return Optional.of(sb.toString());
    }




}
