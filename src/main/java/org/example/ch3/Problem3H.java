package org.example.ch3;


import java.io.*;
import java.util.*;

public class Problem3H {

    static int n;
    static int k;

    static int[] visited = new int[200004];
    static int[] prev = new int[200004];



    public static void main(String[] args) throws IOException{

        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ){
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if(n == k){
                bw.write("0\n");
                bw.write(Integer.toString(n));
                bw.flush();
                return;
            }

            execute();

            Stack<Integer> stack = createStack();

            bw.write(Integer.toString(visited[k] - 1));
            bw.newLine();

            while(!stack.isEmpty()){
                bw.write(Integer.toString(stack.pop()));
                bw.write(' ');
            }
            bw.flush();
        }
    }

    static void execute(){


        Queue<Integer> queue = new LinkedList<>();

        visited[n] = 1;
        queue.add(n);

        while(!queue.isEmpty()){
            int pos = queue.poll();

            if(pos == k){
                return;
            }

            for(int nPos : new int[]{pos-1, pos+1, pos*2}){
                if(nPos < 0 || nPos >= visited.length){
                    continue;
                }
                if(visited[nPos] != 0){
                    continue;
                }

                visited[nPos] = visited[pos] + 1;
                prev[nPos] = pos;
                queue.add(nPos);
            }


        }

    }

    static Stack<Integer> createStack(){
        int cursor = k;
        Stack<Integer> stack = new Stack<>();

        while(cursor != n){
            stack.push(cursor);
            cursor = prev[cursor];
        }

        stack.push(n);
        return stack;
    }


}
