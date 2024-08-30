package org.example.ch;

import java.io.*;
import java.util.*;


public class Problem1475 {

    private static int[] numberCounts = new int[10];

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            String st = br.readLine();

            for(int i = 0; i < st.length(); i++){
                numberCounts[st.charAt(i) - '0']++;
            }

            move69();

            int result = Arrays.stream(numberCounts).max().orElseGet(()->-1);

            bw.write(Integer.toString(result));
            bw.flush();
        }
    }


    private static void move69(){
        int sum = numberCounts[6] + numberCounts[9];

        numberCounts[6] = sum / 2;
        numberCounts[9] = sum / 2 + (sum % 2);

    }
}
