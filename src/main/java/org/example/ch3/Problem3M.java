package org.example.ch3;


import java.io.*;
import java.util.*;


public class Problem3M {

    static final int[] values = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    static int k;

    static char[] operators;
    static final List<String> resultList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

            k = Integer.parseInt(br.readLine());

            operators = new char[k];

            StringTokenizer s = new StringTokenizer(br.readLine());

            for(int i = 0; i < k; i++){
                operators[i] = s.nextToken().charAt(0);
            }

            permutation(k + 1, 0);

            String max = "0";
            String min = "99";

            for(String val : resultList){
                if(max.compareTo(val) < 0){
                    max = val;
                }
                if(min.compareTo(val) > 0){
                    min = val;
                }
            }

            System.out.println(max);
            System.out.println(min);
        }

    }

    // values 배열 중 c개의 원소를 뽑아 나열하는 함수
    public static void permutation(int c, int depth){
        if(depth == c){
            int[] numbers = Arrays.copyOfRange(values, 0, c);

            if(!isAnswer(numbers)){
                return;
            }

            StringBuilder sb = new StringBuilder();

            for(int number : numbers){
                sb.append(number);
            }

            resultList.add(sb.toString());
            return;
        }


        for(int i = depth; i < values.length; i++){
            swap(i, depth);
            permutation(c, depth+1);
            swap(i, depth);
        }

    }

    private static boolean isAnswer(int[] numbers){
        for(int i =0; i < k; i++){
            int num1 = numbers[i];
            int num2 = numbers[i+1];
            char oper = operators[i];

            if(!isTrue(num1, oper, num2)){
                return false;
            }
        }

       return true;
    }

    private static void swap(int i1, int i2){
        int tmp = values[i1];
        values[i1] = values[i2];
        values[i2] = tmp;
    }

    private static boolean isTrue(int num1, char oper, int num2){
        if(oper == '<'){
            return num1 < num2;
        }
        else if(oper == '>'){
            return num1 > num2;
        }

        return false;
    }
}
