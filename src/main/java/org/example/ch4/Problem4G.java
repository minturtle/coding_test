package org.example.ch4;

import java.io.*;
import java.util.*;

public class Problem4G {

    static Stack<Integer> stickStack = new Stack<>();
    static int k;

    public static void main(String[] args) throws IOException{

        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            k = Integer.parseInt(br.readLine());

            stickStack.push(64);
            bw.write(Integer.toString(execute()));
            bw.flush();
        }

    }

    static int execute(){
        int result = 0;
        for(int i = 0; i < 7; i++){
            if(getBit(i)){
                result++;
            }
        }
        return result;
    }

    static boolean getBit(int idx){
        return (k & (1 << idx)) != 0;
    }


}
