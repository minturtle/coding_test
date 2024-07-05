package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5J {


    static Queue<Integer>[] productIdxArr;



    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int hole = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] input = new int[k];
            productIdxArr = new Queue[k + 1];

            for(int i = 0; i <= k; i++){
                productIdxArr[i] = new LinkedList<>();
            }


            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < k; i++){
                input[i] = Integer.parseInt(st.nextToken());
                productIdxArr[input[i]].add(i);
            }

            bw.write(Integer.toString(execute(hole, input)));
            bw.flush();
        }
    }

    static int execute(int holeSize, int[] products){
        int result = 0;

        List<Integer> hole = new LinkedList<>();

        for(int product : products){

            if(hole.contains(product)){
                productIdxArr[product].poll();
                continue;
            }

            if(hole.size() < holeSize){
                productIdxArr[product].poll();
                hole.add(product);
                continue;
            }

            Integer removeProduct = findRemoveProduct(hole);
            hole.remove(removeProduct);

            hole.add(product);
            productIdxArr[product].poll();
            result++;
        }

        return result;
    }

    static Integer findRemoveProduct(List<Integer> hole){
        int max = hole.get(0);

        for(int product : hole){
            if(productIdxArr[product].isEmpty()){
                return product;
            }

            if(productIdxArr[product].peek() > productIdxArr[max].peek()){
                max = product;
            }
        }


        return max;
    }

}

