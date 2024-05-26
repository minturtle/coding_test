package org.example.ch2;


import java.io.*;
import java.util.*;

public class Problem2G {




    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();


        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());



        Integer[] inputArr = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

        Map<Integer, Integer> countArr = getCountArr(inputArr);
        Map<Integer, Integer> indexArr = getIndexArr(inputArr);




        Arrays.sort(inputArr, (n1, n2)->{
            int count1 = countArr.get(n1);
            int count2 = countArr.get(n2);
            if(count1 != count2){
               return count2 - count1;
            }
            else{
                return indexArr.get(n1) - indexArr.get(n2);
            }
        });

        for(int num : inputArr){
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString());
    }






    static Map<Integer, Integer> getCountArr(Integer[] inputArr){
        Map<Integer, Integer> result = new HashMap<>();

        for(int num : inputArr){
            result.putIfAbsent(num, 0);
            Integer i = result.get(num);
            result.put(num, i + 1);
        }

        return result;
    }

    static Map<Integer, Integer> getIndexArr(Integer[] inputArr){
        Map<Integer, Integer> result = new HashMap<>();

        for(int i = 0; i < inputArr.length; i++){
            if(result.containsKey(inputArr[i])){
                continue;
            }
            result.put(inputArr[i], i);
        }

        return result;
    }

}
