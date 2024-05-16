package org.example.ch1;

import java.util.*;
import java.io.*;


public class Problem1K {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        Permutation p = new Permutation();

        String input = br.readLine().trim();

        String result = p.p(input.toCharArray(), input.length());

        if(result == null){
            bw.write("I'm Sorry Hansoo");
        }
        else{
            bw.write(result);
        }

        bw.flush();

        br.close();
        bw.close();
    }

    static class Permutation{

        public String p(char[] n, int r){
            return p(n, r, 0);
        }

        private String p(char[] n, int r, int depth){
            if(depth == r){
                if(isPel(n)){
                    return String.valueOf(n);
                }
                return null;
            }

            for(int i = depth; i < r; i++){
                swap(n, depth, i);
                String result = p(n, r, depth + 1);

                if(result != null){
                    return result;
                }

                swap(n, depth, i);
            }

            return null;
        }

        private void swap(char[] n , int i1, int i2){
            char temp = n[i1];
            n[i1] = n[i2];
            n[i2] = temp;
        }

        private boolean isPel(char[] cArr){
            int length = cArr.length;
            int mid = length / 2;

            for(int i = 0; i < mid; i++){
                if(cArr[i] != cArr[length - 1 - i]){
                    return false;
                }
            }

            return true;
        }

    }


}
