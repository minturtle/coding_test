package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5V {

    public static void main(String[] args) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
           int want = Integer.parseInt(br.readLine());

           StringTokenizer st = new StringTokenizer(br.readLine());

           int m = Integer.parseInt(st.nextToken());
           int n = Integer.parseInt(st.nextToken());

           int[] pizza1 = new int[m];
           int[] pizza2 = new int[n];

            for(int i = 0; i < m; i++){
                pizza1[i] = Integer.parseInt(br.readLine());
            }
            for(int i = 0; i < n; i++){
                pizza2[i] = Integer.parseInt(br.readLine());
            }


            bw.write(Integer.toString(execute(pizza1, pizza2, want)));
            bw.flush();
        }
    }

    static int execute(int[] pizza1, int[] pizza2, int want){
        Map<Integer, Integer> pizza1Made = make(pizza1);
        Map<Integer, Integer> pizza2Made = make(pizza2);

        int result = 0;

        result += getCount(pizza1Made, pizza2Made, want, 0);
        result += getCount(pizza1Made, pizza2Made, 0, want);

        for(int i = 1; i < want; i++){
            result += getCount(pizza1Made, pizza2Made, i, want - i);
        }

        return result;
    }

    static int getCount(Map<Integer, Integer> pizza1Made, Map<Integer, Integer> pizza2Made, int size1, int size2){
        if(size1 == 0){
            return pizza2Made.getOrDefault(size2, 0);
        }
        if(size2 == 0){
            return pizza1Made.getOrDefault(size1, 0);
        }

        int pizza1Cnt = pizza1Made.getOrDefault(size1, 0);
        int pizza2Cnt = pizza2Made.getOrDefault(size2, 0);

        return pizza1Cnt * pizza2Cnt;

    }


    static Map<Integer, Integer> make(int[] pizza){
        Map<Integer, Integer> result = new HashMap();

        for(int size = 1; size < pizza.length; size++){
            for(int i = 0; i < pizza.length; i++){
                int idx = i;
                int tmp = 0;

                for(int j = 0; j < size; j++){
                    tmp += pizza[idx];
                    idx = (idx + 1) % pizza.length;
                }

                Integer value = result.getOrDefault(tmp, 0);
                result.put(tmp, ++value);
            }
        }

        int allSum = Arrays.stream(pizza).sum();
        Integer value = result.getOrDefault(allSum, 0);
        result.put(allSum, ++value);
        return result;
    }
}
