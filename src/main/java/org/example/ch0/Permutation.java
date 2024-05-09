package org.example.ch0;

import java.io.*;
import java.util.*;

public class Permutation {


    public static void main(String ... args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] nr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();



        P per = new P(arr);


        per.p(nr[0], nr[1]);
    }

}

class P{

    private final int[] arr;


    public P(int[] arr) {
        this.arr = arr;
    }

    public void p(int n , int r){
        p(n, r, 0);
    }


    private void p(int n, int r, int depth){
        if(depth == r){
            print(r);
        }

        for(int i = depth; i < n; i++){
            swap(depth, i);
            p(n, r, depth + 1);
            swap(depth, i);
        }


    }

    private void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void print(int size){
        for(int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
