package org.example.ch2;

import java.io.*;
import java.util.*;
public class Problem2M {



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 0;
        int n = Integer.parseInt(br.readLine().trim());

        int num = 665;

        while(cnt < n){
            num++;
            if(Integer.toString(num).contains("666")){
                cnt++;
            }
        }

        System.out.println(num);

    }

}
