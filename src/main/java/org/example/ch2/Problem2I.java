package org.example.ch2;

import java.io.*;
import java.util.*;

public class Problem2I {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());


        List<String> result = new ArrayList<>(100 * n);

        for(int i = 0 ; i < n; i++){
            String inputStr = br.readLine().trim();

            List<String> numbers = getNumbers(inputStr);

            result.addAll(numbers);
        }


        result.sort((n1, n2)->{
            if(n1.length() != n2.length()){
                return n1.length() - n2.length();
            }
            return n1.compareTo(n2);
        });

        for(String num : result){
            bw.write(num);
            bw.newLine();
        }



        bw.flush();
        br.close();
        bw.close();


    }

    static List<String> getNumbers(String str){
        List<String> result = new ArrayList<>(str.length());

        StringBuilder number = new StringBuilder();
        boolean numberflag = false;
        char[] cArr = str.toCharArray();

        for(char c : cArr){
            if(isNum(c)){
                number.append(c);
                numberflag = true;
                continue;
            }

            if(numberflag){
                result.add(removePrevZero(number.toString()));

                number = new StringBuilder();
                numberflag = false;
            }

        }

        if(numberflag){
            result.add(removePrevZero(number.toString()));
        }




        return result;
    }

    static boolean isNum(char c){
        return c >= '0' && c <= '9';
    }

    static String removePrevZero(String s){
        StringBuilder result = new StringBuilder();


        for(int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);

            if(c == '0' && result.length() == 0){
                continue;
            }
            result.append(c);
        }

        if(result.length() == 0){
            return "0";
        }

        return result.toString();

    }

}
