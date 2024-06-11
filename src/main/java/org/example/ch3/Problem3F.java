package org.example.ch3;


import java.io.*;
import java.util.*;


public class Problem3F {


    static int n;
    static int[] operand;
    static char[] operator;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        n = Integer.parseInt(br.readLine().trim());
        String input = br.readLine().trim();

        operand = new int[(n + 1) / 2];
        operator = new char[n / 2];

        for(int i = 0, p1 = 0, p2 = 0; i < n; i++){
            if(i % 2 == 0){
                operand[p1++] = input.charAt(i) - '0';
            }
            else{
                operator[p2++] = input.charAt(i);
            }
        }

        int result = execute(0, operand[0]);

        System.out.println(result);
    }


    static int execute(int cursor, int value){

        if(cursor == operand.length - 1){
            return value;
        }

        int max = execute(cursor + 1, calculate(operator[cursor], value, operand[cursor + 1]));


        if(cursor + 2 <= operand.length - 1){
            int temp = calculate(operator[cursor + 1], operand[cursor + 1], operand[cursor + 2]);
            int val = execute(cursor + 2, calculate(operator[cursor], value, temp));
            max = Math.max(max, val);
        }

        return max;
    }

    static int calculate(char oper, int o1, int o2){
        switch (oper){
            case '+':
                return o1 + o2;
            case '-':
                return o1 - o2;
            case '*':
                return o1 * o2;
        }
        return -1;
    }
}
