package org.example.ch4;

import java.io.*;
import java.util.StringTokenizer;

public class Problem4M {

    public static void main(String[] args) throws IOException {
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){

            StringTokenizer st = new StringTokenizer(br.readLine());

            String s1 = st.nextToken();
            String s2 = st.nextToken();

            int[] num1 = initNumber(s1);
            int[] num2 = initNumber(s2);

            int[] result = add(num1, num2);

            StringBuilder sb = new StringBuilder();

            for(int i = result.length - 1; i >= 0; i--){
                if(i == result.length - 1 && result[i] == 0){
                    continue;
                }

                sb.append(result[i]);
            }

            bw.write(sb.toString());
            bw.flush();
        }
    }


    static int[] initNumber(String s){
        int size = s.length();

        int[] result = new int[size];

        for(int i = 0; i < s.length(); i++){
            result[i] = s.charAt(size-i-1) - '0';
        }
        return result;
    }


    static int[] add(int[] num1, int[] num2){
        int resultSize = Math.max(num1.length , num2.length) + 1;
        int[] result = new int[resultSize];

        for(int i = 0; i < resultSize - 1; i++){
            int n1 = 0;
            int n2 = 0;


            if(num1.length > i){
                n1 = num1[i];
            }
            if(num2.length > i){
                n2 = num2[i];
            }


            int r1 = n1 + n2;

            if(r1 >= 10){
                result[i + 1] += r1 / 10;
            }
            result[i] += r1 % 10;
        }

        return result;
    }
}
