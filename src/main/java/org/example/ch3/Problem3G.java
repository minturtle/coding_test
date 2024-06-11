package org.example.ch3;


import java.io.*;
import java.util.*;

public class Problem3G {

    static int count = 0;
    static int[] visited = new int[100001];

    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String [] args) throws IOException{
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            int pos1 = Integer.parseInt(st.nextToken());
            int pos2 = Integer.parseInt(st.nextToken());

            if(pos1 == pos2){
                bw.write("0\n1");
                bw.flush();
                return;
            }

            execute(pos1, pos2);

            bw.write(Integer.toString(visited[pos2]));
            bw.newLine();
            bw.write(Integer.toString(count));

            bw.flush();
        }
    }

    static void execute(int pos1, int pos2){
        queue.add(pos1);

        while(!queue.isEmpty()) {
            int pos = queue.poll();

            // 최단경로와 동일한 비용으로 도착한 경우
            if(pos == pos2){
                count++;
                continue;
            }



            for(int npos : new int[]{pos + 1, pos -1, pos * 2}){
                if(npos < 0 || npos > 100000){
                    continue;
                }

                int weight = visited[pos] + 1;

                // npos까지 최단 경로가 아닌 경우
                if(visited[npos] != 0 && visited[npos] < weight){
                    continue;
                }
                visited[npos] = weight;
                queue.add(npos);
            }


        }
    }
}
