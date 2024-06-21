package org.example.ch3;

import java.util.*;
import java.io.*;

public class Problem3N {

    static int k;
    static char[] input;

    static List<Character>[] levelNodes;


    public static void main(String[] args) throws IOException{
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            k = Integer.parseInt(br.readLine());

            input = new char[(int)Math.pow(2, k) - 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < input.length; i++){
                input[i] = st.nextToken().charAt(0);
            }

            levelNodes = new List[k];

            for(int i = 0; i < k; i++){
                levelNodes[i] = new LinkedList<>();
            }

            execute(0, input.length - 1, 0);

            for(int i = 0; i < k; i++){
                for(char c : levelNodes[i]){
                    bw.write(c);
                    bw.write(' ');
                }
                bw.newLine();
            }


            bw.flush();
        }
    }


    static void execute(int start, int end, int depth){
        if(start > end){
            return;
        }

        int mid = (start + end) / 2;
        levelNodes[depth].add(input[mid]);

        execute(start, mid - 1, depth+1);
        execute(mid + 1, end, depth+1);
    }



}
