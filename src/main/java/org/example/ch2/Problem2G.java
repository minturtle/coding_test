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
        inputOrderSet = new LinkedHashSet<>(c);
        int[] countArr = getCountArr(c, inputArr);

        for(int i = 0; i < inputOrderSet.size(); i++){
            int maxNum = findMaxNum(countArr);

            for(int j = 0 ; j < countArr[maxNum]; j++){
                sb.append(maxNum).append(" ");
            }
            countArr[maxNum] = 0; //한번 탐색된거는 0으로 바꿔 다시 탐색되지 못하게 함.
        }

        System.out.println(sb.toString());

    }

    // 빈도수가 가장 높은 숫자를 반환하는 함수
    static int findMaxNum(int[] countArr){
        int maxNum = 0;

        for(int num : inputOrderSet){
            // count가 같은 경우 order가 빠른 순으로 우선순위를 부여하기 위해, 무조건 큰 값이여야지만 max를 변경
            if(countArr[maxNum] >= countArr[num]){
                continue;
            }

            maxNum = num;
        }

        return maxNum;
    }



    static int[] getCountArr(int maxNum, int[] arr){
        int[] result = new int[maxNum+1];

        for(int num : arr){
            result[num]++;
            inputOrderSet.add(num);
        }

        return result;
    }

}
