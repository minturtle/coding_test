package org.example.ch6;

import java.io.*;
import java.util.*;

public class Problem6L {

    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());
            double[] numbers = new double[n];

            for(int i = 0; i < n; i++){
                numbers[i] = Double.parseDouble(br.readLine());
            }

            bw.write(String.format("%.3f", execute(numbers)));
        }
    }

    static double execute(double[] numbers){
        double tmp = 1;
        double result = Double.MIN_VALUE;

        for(double number : numbers){
            tmp = Math.max(1, tmp);
            tmp *= number;
            result = Math.max(result, tmp);
        }

        return result;
    }
}
