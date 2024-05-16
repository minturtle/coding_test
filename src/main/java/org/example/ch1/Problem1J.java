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
        Combination c= new Combination();

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

        String[] categories = clotheCategoryCountMap.keySet().toArray(new String[]{});


        int total = 0;


        // nCr 계산(n == 카테고리 갯수, r == 1 ~ 카테고리 갯수)
        for(int i = 1; i <= categories.length; i++){
            List<Stack<String>> selectResult = c.c(categories, i);


            // 뽑기 결과에 대해 뽑힌 카테고리 갯수 * 카테고리 갯수로 경우의 수를 구함
            for(int j = 0; j < selectResult.size(); j++){
                Stack<String> selected = selectResult.get(j);

                int cnt = 1;
                while(!selected.empty()){
                    int categoryCnt =  clotheCategoryCountMap.get(selected.pop());
                    cnt *= categoryCnt;
                }

                total += cnt;
            }
        }


        bw.write(Integer.toString(total));
        bw.newLine();
    }


    static class Combination{

        public List<Stack<String>> c(String[] n , int r){
            Stack<String> selected = new Stack<>();

            return c(n, r, selected, 0);
        }


        private List<Stack<String>> c(String[] n, int r, Stack<String> selected, int start){
            ArrayList<Stack<String>> result = new ArrayList<>();
            if(selected.size() == r){
                result.add((Stack<String>)selected.clone());
                return result;
            }



            for(int i = start; i < n.length; i++){
                selected.push(n[i]);
                result.addAll(c(n, r, selected, i + 1));
                selected.pop();
            }

            return result;
        }

    }



}
