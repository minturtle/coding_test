package org.example.ch0;

import java.io.*;
import java.util.*;

public class Combination {

    public static void main(String ... args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int [] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int [] nr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        C c = new C(arr);

        c.c(nr[0], nr[1]);


    }

}


class C{

    private final int[] arr;

    public C(int[] arr) {
        this.arr = arr;
    }

    public void c(int n, int r){
       c(n , r, new Stack<>(), 0);
    }

    public void c(int n, int r, Stack<Integer> li, int start){
        if(li.size() == r){
            print(li);
            return;
        }

        for(int i = start; i < n; i++){
            li.push(arr[i]);
            c(n, r, li, i + 1);
            li.pop();
        }


    }

    private void print(Stack<Integer> li){
        for(int i = 0; i < li.size(); i++){
            System.out.print(li.get(i) + " ");
        }
        System.out.println();
    }

}