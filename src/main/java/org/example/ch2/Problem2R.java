package org.example.ch2;


import java.io.*;
import java.util.*;

public class Problem2R {

    static int n;

    static int[] input;



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        input = new int[n];


        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < n; i++){
            // 부모 노드
            int p = Integer.parseInt(st.nextToken());
            input[i] = p;

        }

        int x = Integer.parseInt(br.readLine().trim());

        del(x);
        System.out.println(getLeafNodeCnt());

        br.close();
    }


    static void del(int x){
        input[x] = -2;
        for(int i = 0; i < n; i++){
            if(input[i] == x){
                del(i);
            }
        }
    }

    static int getLeafNodeCnt(){
        int cnt = 0;

        for(int i = 0; i < n; i++){
            if(input[i] == -2){
                continue;
            }
            if(!hasChild(i)){
                cnt++;
            }

        }
        return cnt;
    }

    static boolean hasChild(int i){
        for(int j = 0; j < n; j++){
            if(input[j] == i){
                return true;
            }
        }
        return false;
    }

}
