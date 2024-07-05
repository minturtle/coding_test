package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5J {




    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int hole = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] input = new int[k];
            int[] productCounts = new int[k+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < k; i++){
                input[i] = Integer.parseInt(st.nextToken());
                productCounts[input[i]]++;
            }

            bw.write(Integer.toString(execute(hole, input, productCounts)));
            bw.flush();
        }
    }

    static int execute(int holeSize, int[] products,  int[] productRemainCounts){
        int result = 0;

        List<Integer> hole = new LinkedList<>();

        for(int product : products){

            if(hole.contains(product)){
                productRemainCounts[product]--;
                continue;
            }

            if(hole.size() < holeSize){
                hole.add(product);
                productRemainCounts[product]--;
                continue;
            }

            Integer minProduct = findMinRemainProduct(hole, productRemainCounts);
            hole.remove(minProduct);

            hole.add(product);
            productRemainCounts[product]--;
            result++;
        }

        return result;
    }

    static Integer findMinRemainProduct(List<Integer> hole, int[] productRemains){
        int min = hole.get(0);

        for(Integer product : hole){
            if(productRemains[product] >= productRemains[min]){
                continue;
            }
            min = product;
        }

        return min;
    }

}

