package org.example.ch5;

import java.io.*;
import java.util.*;


public class Problem5G {


    static int n;

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            n = Integer.parseInt(br.readLine());

            bw.write(Integer.toString(execute()));
            bw.flush();
        }
    }

    static int execute(){
        int result = 0;

        List<Integer> primeList = getPrimeList();


        for(int i = 0; i < primeList.size(); i++){
            if(!dfs(i, primeList, 0)){
                continue;
            }

            result++;
        }

        return result;
    }


    static List<Integer> getPrimeList(){
        List<Integer> result = new ArrayList<>(n);

        for(int i = 2; i <= n; i++){
            if(!isPrime(i)){
                continue;
            }

            result.add(i);
        }

        return result;
    }


    static boolean isPrime(int num){
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }

        return true;
    }


    static boolean dfs(int idx, List<Integer> primeNumbers, int sum){
        sum += primeNumbers.get(idx);

        if(sum > n){
            return false;
        }
        if(sum == n){
            return true;
        }
        if(idx + 1 >= primeNumbers.size()){
            return false;
        }

        return dfs(idx + 1, primeNumbers, sum);

    }
}
