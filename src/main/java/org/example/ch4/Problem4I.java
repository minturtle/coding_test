package org.example.ch4;

import java.io.*;
import java.util.*;


public class Problem4I {

    static final String ADD_CMD = "add";
    static final String REMOVE_CMD = "remove";
    static final String CHECK_CMD = "check";
    static final String TOGGLE_CMD = "toggle";
    static final String ALL_CMD = "all";
    static final String EMPTY_CMD = "empty";


    static int set = 0;

    public static void main(String[] args )throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            int n = Integer.parseInt(br.readLine());

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                switch (command){
                    case ADD_CMD :
                        add(Integer.parseInt(st.nextToken()));
                        break;
                    case REMOVE_CMD :
                        remove(Integer.parseInt(st.nextToken()));
                        break;
                    case CHECK_CMD :
                        bw.write(check(Integer.parseInt(st.nextToken())) ? '1' : '0');
                        bw.newLine();
                        break;
                    case TOGGLE_CMD :
                        toggle(Integer.parseInt(st.nextToken()));
                        break;
                    case ALL_CMD :
                        all();
                        break;
                    case EMPTY_CMD :
                        empty();
                }


            }
            bw.flush();
        }
    }



    static void add(int i){
        set |= (1 << i);
    }

    static void remove(int i){
        set &= ~(1 << i);
    }

    static boolean check(int i){
        return (set & (1 << i)) != 0;
    }

    static void toggle(int i){
        set ^= (1 << i);
    }

    static void all(){
        set = ~0;
    }

    static void empty(){
        set = 0;
    }
}
