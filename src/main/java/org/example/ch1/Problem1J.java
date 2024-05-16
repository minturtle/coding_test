package org.example.ch1;

import java.io.*;
import java.util.*;

public class Problem1J {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < testCase; i++){
            doTest(br, bw);
        }


        bw.flush();

        br.close();
        bw.close();
    }

    static void doTest(BufferedReader br, BufferedWriter bw) throws IOException{

        Map<String , Integer> clotheCategoryCountMap = new HashMap<>();


        int clotheTotalCount = Integer.parseInt(br.readLine().trim());


        // 먼저 각 의상 카테고리(headgear, eyewear ...)의 갯수를 측정
        for(int i = 0; i < clotheTotalCount; i++){
            String clotheCategory = br.readLine().trim().split(" ")[1];

            if(!clotheCategoryCountMap.containsKey(clotheCategory)){
                clotheCategoryCountMap.put(clotheCategory, 1);
                continue;
            }
            int categoryCount = clotheCategoryCountMap.get(clotheCategory);
            clotheCategoryCountMap.put(clotheCategory, categoryCount+1);

        }

        Collection<Integer> values = clotheCategoryCountMap.values();
        int result = 1;

        for(int value : values){
            result *= (value + 1);
        }

        bw.write(Integer.toString(result - 1)); // 아무것도 안입는 경우의 수는 제외하기 때문에 -1
        bw.newLine();
        bw.flush();

    }


}
