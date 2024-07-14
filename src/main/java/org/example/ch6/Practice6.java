package org.example.ch6;

import java.io.*;
import java.util.*;

public class Practice6{

}
class LIS1 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(Integer.toString(execute(numbers)));
        }
    }

    static int execute(int[] numbers){
        int max = 0;
        int[] cnt = new int[numbers.length];


        for(int i = 0; i < numbers.length; i++){
            int maxVal = 0;

            for(int j = 0; j < i; j++){
                if(numbers[i] <= numbers[j]){
                    continue;
                }
                maxVal = Math.max(maxVal, cnt[j]);
            }

            cnt[i] = ++maxVal;
            max = Math.max(max, maxVal);
        }


        return max;
    }

}
class LIS4 {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] numbers = new int[n];
            for(int i = 0; i < n; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> result = execute(numbers);

            StringJoiner sj = new StringJoiner(" ");
            int i = 0;
            for(int num : result){
                i++;
                sj.add(Integer.toString(num));
            }

            bw.write(i + "\n");
            bw.write(sj.toString());
            bw.flush();
        }
    }

    static List<Integer> execute(int[] numbers){
        int max = Integer.MIN_VALUE;

        List<List<Integer>> subsetList = new ArrayList<>(numbers.length);

        for(int i = 0; i < numbers.length; i++){
            subsetList.add(new ArrayList<>(i + 1));
        }

        for(int i = 0; i < numbers.length; i++){
            int maxIdx = -1;
            int maxSize = 0;

            List<Integer> numberSubset = subsetList.get(i);

            for(int j = 0; j < i; j++){
                if(numbers[i] <= numbers[j]){
                    continue;
                }
                if(maxSize > subsetList.get(j).size()){
                    continue;
                }

                maxSize = subsetList.get(j).size();
                maxIdx = j;

            }

            if(maxIdx != -1){
                numberSubset.addAll(subsetList.get(maxIdx));
            }

            numberSubset.add(numbers[i]);

            if(max == Integer.MIN_VALUE || subsetList.get(max).size() < maxSize + 1){
                max = i;
            }
        }



        return subsetList.get(max);
    }
}
