package org.example.ch3;


import java.io.*;
import java.util.*;

public class Problem3H {

    static int n;
    static int k;

    static int minWeight;
    static int[] fastestRoute;

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
            bw.write(Integer.toString(minWeight));

            bw.newLine();
            for(int r : fastestRoute){
                bw.write(Integer.toString(r));
                bw.write(' ');
            }

            bw.flush();
        }
    }

    static void execute(){
        boolean[] visited = new boolean[100001];
        Queue<int[]> queue = new LinkedList<>();

        visited[n] = true;
        queue.add(new int[]{n});

        while(!queue.isEmpty()){
            int[] route = queue.poll();

            int lastPos = route[route.length - 1];
            if(lastPos == k){
                minWeight = route.length - 1;
                fastestRoute = route;
                return;
            }

            for(int nPos : new int[]{lastPos -1, lastPos + 1, lastPos * 2}){
                if(nPos < 0 || nPos > 100000){
                    continue;
                }
                if(visited[nPos]){
                    continue;
                }

                visited[nPos] = true;
                queue.add(createNewRoute(route, nPos));

            }


        }

    }


    static int[] createNewRoute(int[] route, int val){
        int[] result = new int[route.length + 1];
        for(int i = 0; i < route.length; i++){
            result[i] = route[i];
        }

        result[route.length] = val;
        return result;
    }
}
