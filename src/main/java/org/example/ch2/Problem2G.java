package org.example.ch2;


import java.io.*;
import java.util.*;

public class Problem2G {


    static Set<Integer> inputOrderSet; // 탐색 순서 보장, 중복 X

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();


        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());



        int[] inputArr = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        inputOrderSet = getInputOrderSet(inputArr);
        Map<Integer, Integer> countArr = getCountArr(c, inputArr);

        for(int i = 0; i < inputOrderSet.size(); i++){
            int maxNum = findMaxNum(countArr);

            for(int j = 0 ; j < countArr.get(maxNum); j++){
                sb.append(maxNum).append(" ");
            }
            countArr.put(maxNum, 0); //한번 탐색된거는 0으로 바꿔 다시 탐색되지 못하게 함.
        }

        System.out.println(sb.toString());

    }

    static Set<Integer> getInputOrderSet(int[] inputArr){
        Set<Integer> set = new LinkedHashSet<>();

        for(int num : inputArr){
            set.add(num);
        }

        return set;
    }

    // 빈도수가 가장 높은 숫자를 반환하는 함수
    static int findMaxNum(Map<Integer, Integer> countArr){
        int maxNum = (int)inputOrderSet.toArray()[0];

        for(int num : inputOrderSet){
            // count가 같은 경우 order가 빠른 순으로 우선순위를 부여하기 위해, 무조건 큰 값이여야지만 max를 변경
            if(countArr.get(maxNum) >= countArr.get(num)){
                continue;
            }

            maxNum = num;
        }

        return maxNum;
    }



    static Map<Integer, Integer> getCountArr(int maxNum, int[] arr){
        Map<Integer, Integer> result = new HashMap<>();

        for(int num : arr){
            result.putIfAbsent(num, 0);
            Integer i = result.get(num);
            result.put(num, i + 1);
        }

        return result;
    }

}
