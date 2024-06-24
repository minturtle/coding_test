package org.example.ch4;

public class Practice4 {


    static char[] arr = new char[]{'a', 'b', 'c', 'd'};


    public static void main(String[] args) {

        for(int i = 0; i < arr.length; i++){
            go(1 | 1 << i);
        }

    }


    static void go(int num){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            if((num & 1 << i) == 0){
                continue;
            }
            sb.append(arr[i]);

        }

        System.out.println(sb);
    }
}
