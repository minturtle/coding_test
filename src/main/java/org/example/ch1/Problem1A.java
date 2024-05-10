package org.example.ch1;

import java.util.*;
import java.io.*;


public class Problem1A {


    public static void main(String ... args) throws IOException{


        int[] input = new int[9];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i < 9; i++){
            input[i] = Integer.parseInt(br.readLine().trim());
        }

        C c = new C(input);
        List<Integer> result = c.c(9, 2);

        Arrays.sort(input);

        for(int num : input){
            if(result.contains(num)){
                continue;
            }

            System.out.println(num);
        }
    }

    private static class C{

        private final int[] arr;
        private final int allSum;


        public C(int[] arr){
            this.arr = arr;
            int allSum = 0;

            for(int num : arr){
                allSum += num;
            }

            this.allSum = allSum;
        }


        public List<Integer> c(int n, int r){
            return c(n ,r, new Stack<>(), 0);
        }


        private List<Integer> c(int n, int r, Stack<Integer> li, int start){

            if(li.size() == r && checkCondition(li)){
                // 해를 발견하는 경우
                return li;
            }

            for(int i = start; i < n; i++){
                li.push(arr[i]);

                // i번째 요소를 집어넣어서 해가 존재하는 경우 해, 존재하지 않는 경우 NULL 반환
                List<Integer> result = c(n, r, li, i + 1);

                // 해가 여러개일 경우 아무거나 출력하면 되므로 해가 존재한다면 바로 종료
                if(result != null){
                    return result;
                }
                li.pop();
            }


            // 앞서 뽑은 숫자에 대해서는 답이 존재하지 않는 경우
            return null;
        }

        private boolean checkCondition(List<Integer> li){
            return allSum - sum(li) == 100;
        }


        private int sum(List<Integer> li){
            int s = 0;
            for(int i = 0; i < li.size();i++){
                s += li.get(i);
            }
            return s;
        }



    }


}



