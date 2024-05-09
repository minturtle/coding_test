package org.example.ch1;

import java.util.*;
import java.io.*;


public class Problem2309 {


    public static void main(String ... args) throws IOException{


        int[] input = new int[9];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i < 9; i++){
            input[i] = Integer.parseInt(br.readLine().trim());
        }

        C c = new C(input);
        List<Integer> result = c.c(9, 7);

        Collections.sort(result);

        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i));
        }
    }


}


class C{

    private final int[] arr;



    public C(int[] arr){
        this.arr = arr;


    }


    public List<Integer> c(int n, int r){
        return c(n ,r, new Stack<>(), 0);
    }


    private List<Integer> c(int n, int r, Stack<Integer> li, int start){
        if(li.size() == r && sum(li) == 100){
            return li;
        }

        for(int i = start; i < n; i++){
            li.push(arr[i]);
            List<Integer> result = c(n, r, li, i + 1);
            if(result != null){
                return result;
            }
            li.pop();
        }

        return null;
    }


    private int sum(List<Integer> li){
        int s = 0;
        for(int i = 0; i < li.size();i++){
            s += li.get(i);
        }
        return s;
    }



}
